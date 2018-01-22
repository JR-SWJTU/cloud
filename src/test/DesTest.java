import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2018/1/8.
 */
public class DesTest {

    public static void main(String args[]){
        try {
            SecureRandom sr = new SecureRandom();
            //为我们选择的DES算法生成一个KeyGenerator对象
            KeyGenerator kg = KeyGenerator.getInstance ("DES" );
            kg.init (sr);
            //生成密钥
            Key key = kg.generateKey();
            System.out.println(key.getFormat());
            System.out.println(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
