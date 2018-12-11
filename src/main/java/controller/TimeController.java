package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
    @GetMapping("/worldclock")
    //@RequestParam(...) khai báo tham số của phương thức xử lý, ràng buộc với tham số request
    public String getTimeByTimezone(ModelMap model, @RequestParam(name="city",required = false,defaultValue = "Asia/Ho_Chi_Minh")String city){
        //lấy thời gian gần nhất tại địa điểm
        Date date = new Date();
        // lấy giờ tại địa điểm
        TimeZone local = TimeZone.getDefault();
        //lấy giờ tại thành phố được chọn
        TimeZone locale = TimeZone.getTimeZone(city);
// tính thời gian hiện tại trong thành phố được chọn ( local_time)
        //getRawOffset() trả về lượng thời gian tính bằng mili giây để thêm vào UTC để có tgian chuẩn nhất
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
// đặt lại ngày theo local_time
        date.setTime(locale_time);
// chuyển dữ liệu cho view
        model.addAttribute("city",city);
        model.addAttribute("date",date);

        return "index";
    }
}
