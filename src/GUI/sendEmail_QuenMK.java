/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author MyPC
 */
public class sendEmail_QuenMK {

    public void sendmail(String mail, int ver) {
        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("hellomoinguinhe@gmail.com", "hello135792468")); //Nhớ nhập đúng với tài khoản thật nhé :))

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("hellomoinguinhe@gmail.com", "Công chúa phép thuật");

            // Tiêu đề
            email.setSubject("Ta sẽ trùng trị ngươi"); //Tiêu đề khi gửi email

            // Nội dung email
            //String covert = String.valueOf(rand);
            email.setMsg("Mã code: " + ver); //Nội dung email bạn muốn gửi.
            // Người nhận
            email.addTo(mail); //Đia chỉ email người nhận
            email.send(); //Thực hiện gửi.
            System.err.println("Gửi email thành công ! Vui lòng kiểm tra email !");
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Gửi không thành công !" + e.toString());
        }
    }

    public static void main(String[] args) {
        try {
            sendEmail_QuenMK sm = new sendEmail_QuenMK();
            // sm.sendmail();
            sm.sendmail("duchuy01102001@gmail.com", 111);
        } catch (Exception e) {
            System.out.println("Loi o: " + e.toString());
        }
    }

}
