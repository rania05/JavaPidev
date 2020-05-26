/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.utils;

/**
 *
 * @author ASUS
 */

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

public class sms {
    public void sendsms(String titre_article) throws IOException, NexmoClientException {
       
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("1878f1e9")
                .apiSecret("7JTSfyTXikC4JZ1A")
                .build();
     /*   // TextMessage message = new TextMessage("Heart2Hold", "21623334418", titre_article);
        System.out.println(message.getMessageBody());
        client.getSmsClient().submitMessage(message);
        
        
        */
        
        TextMessage message = new TextMessage("Heart2Hold"," 21623334418",
        "A text message sent using the Nexmo SMS API");

    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
        
    }
    
    
    
    
}
