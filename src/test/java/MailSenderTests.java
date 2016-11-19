import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import secretsanta.Application;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by allan.moso on 11/16/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class MailSenderTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSend() throws MessagingException {
        final MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom("moso.sender@gmail.com");
        msg.setRecipients(Message.RecipientType.TO, "allan.moso@gmail.com");
        msg.setSubject("Test");
        msg.setText("This is a test");
        javaMailSender.send(msg);
    }
}
