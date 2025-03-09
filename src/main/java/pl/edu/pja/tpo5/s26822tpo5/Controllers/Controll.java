package pl.edu.pja.tpo5.s26822tpo5.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class Controll {
    private StringBuilder chars;
    @PostMapping("/base")
    @ResponseBody
    public String calc(@RequestParam String num, @RequestParam int oldb, @RequestParam int newb){
        this.chars=new StringBuilder("0123456789");
        for(int i='a';i-'a'<90;i++)
        {
            chars.append((char)i);
        }
        String s= """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Base converter</title>
                    <link rel="stylesheet" href="stylesheets/base.css">
                </head>
                <body>
                <div class="centered-container">
                    <h1>Base converter</h1>
                %s</div>
                <img src="rightg.png" alt="Right Image" style="position: absolute; top: 500px; right: 100px;">
                <img src="leftg.png" alt="Left Image" style="position: absolute; top: 500px; left: 100px;">
                </body>
                </html>
                """;
        if(oldb<2||oldb>100||newb<2||newb>100)
            return String.format(s,"<h2>The number doesn't fit the base or the base is out of range 2-100</h2>");
        else
        {
            for(int i=0;i<num.length();i++)
            {
                if(this.chars.charAt(oldb-1)<num.charAt(i)||num.charAt(i)<'0')
                {
                    return String.format(s,"<h2>The number doesn't fit the base or the base is out of range 2-100</h2>");
                }
            }
        }

        return String.format(s,"<h2>In base "+newb +": " +rebase(num, oldb, newb)+ "</h2>" +
                "<h2>In base 2: " +rebase(num, oldb, 2)+ "</h2>" +
                "<h2>In base 8: " +rebase(num, oldb, 8)+ "</h2>" +
                "<h2>In base 10: " +rebase(num, oldb, 10)+ "</h2>" +
                "<h2>In base 16: " +rebase(num, oldb, 16)+ "</h2>");
    }
    public String rebase(String num, int oldb, int newb)
    {
        int a=0;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<num.length();i++)
        {
            a+=(int)Math.pow(oldb,num.length()-i-1)*this.chars.indexOf(""+ num.charAt(i));
        }
        while(a>=newb)
        {
            stringBuilder.append(this.chars.charAt(a%newb));
            a/=newb;
        }
        stringBuilder.append(this.chars.charAt(a));
        return stringBuilder.reverse().toString();
    }
}