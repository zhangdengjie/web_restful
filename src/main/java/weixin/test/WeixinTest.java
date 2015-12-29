package weixin.test;


import weixin.pojo.AccessToken;
import weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken("wxf67624d1cc5df2e0", "d4624c36b6795d1d99dcf0547af5443d");
			//System.out.println("票据"+token.getToken());
			//System.out.println("有效时间"+token.getExpiresIn());
			
			String path = "D:/imooc.jpg";
			String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			System.out.println(mediaId);
			
//			String result = WeixinUtil.translate("my name is laobi");
//			String result = WeixinUtil.translateFull("");
//			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
