package pl.edu.pja.tpo5.s26822tpo5.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Controller
public class AController {

    @RequestMapping(path = "/current-time", method = RequestMethod.GET)
    @ResponseBody
    public String time(@RequestParam(required = false,defaultValue = "0") String hours,
                       @RequestParam(required = false,defaultValue = "hh:mm:ss.SSSS YYYY/MM/DD") String pattern){
        String s= """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Current time</title>
                    <link rel="stylesheet" href="stylesheets/base.css">
                </head>
                <body>
                <div class="centered-container">
                <h1>%s</h1></div>
                <img src="rightg.png" alt="Right Image" style="position: absolute; top: 500px; right: 100px;">
                <img src="leftg.png" alt="Left Image" style="position: absolute; top: 500px; left: 100px;">
                </body>
                </html>
                """;
        String formattedTime;
        try {
            OffsetDateTime utcPlusXTime = OffsetDateTime.now(ZoneOffset.ofHours(Integer.parseInt(hours)));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            formattedTime=String.format(s,"Current time in UTC+"+hours+":  "+ utcPlusXTime.format(formatter));
        }catch (Exception e)
        {
            OffsetDateTime utcPlusXTime = OffsetDateTime.now(ZoneOffset.ofHours(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSSS YYYY/MM/DD");
            formattedTime=String.format(s,"Current time in UTC+0"+":  "+ utcPlusXTime.format(formatter)+" "+e.getMessage());
        }
        return (formattedTime);
    }
    @RequestMapping(path = "/current-year",method = RequestMethod.GET)
    @ResponseBody
    public String year(){
        String s= """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Current year</title>
                    <link rel="stylesheet" href="stylesheets/base.css">
                </head>
                <body>
                <div class="centered-container">
                <h1>%s</h1></div>
                <img src="rightg.png" alt="Right Image" style="position: absolute; top: 500px; right: 100px;">
                <img src="leftg.png" alt="Left Image" style="position: absolute; top: 500px; left: 100px;">
                </body>
                </html>
                """;
        return String.format(s,"Current year: "+Year.now());
    }
}