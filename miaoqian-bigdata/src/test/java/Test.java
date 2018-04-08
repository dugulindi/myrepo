import com.miaoqian.bigdata.common.utils.DateUtils;

import java.util.Date;


/**
 * Created by admin on 2017/7/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("12$34".split("\\$")[0]);
        String date = DateUtils.getYyyymmddFormat().format(new Date());
        String reg = ","+ date;
        String s = "2017-07-18-10-20-06-529 | WEB | CAMEL-PROJECT | SUCCESS | fb1bdb67b7984919b5fa1f4649924144-01-00-01 | UserController-userinfo | 567 | 10.0.1.151 | 127.0.0.1 | /user/info | HTTP | POST | 200 | | | | | | ,2017-07-18-10-20-07-457 | WEB | CAMEL-PROJECT | SUCCESS | 73487998eb9541a78f99395caf073244-01-00-01 | EnterpriseContorller-enterpriseEdit | 446 | 10.0.1.151 | 127.0.0.1 | /enterprise/enterpriseedit | HTTP | GET | 200 | | | | | | ,2017-07-18-10-20-08-535 | WEB | CAMEL-PROJECT | SUCCESS | f987d862f38548e397940dd63eae80d4-01-00-01 | ProjectController-bussinessStatus | 708 | 10.0.1.151 | 127.0.0.1 | null?_=1500344406429 | HTTP | GET | 200 | | | | | | ,2017-07-18-10-20-09-457 | WEB | CAMEL-PROJECT | SUCCESS | 6684d22d26014ee6842261164b1a5f74-01-00-01 | GuaranteeController-guaranteeMethodType | 581 | 10.0.1.151 | 127.0.0.1 | null?_=1500344406436 | HTTP | GET | 200 | | | | | | ,2017-07-18-10-20-10-271 | WEB | CAMEL-PROJECT | SUCCESS | 699e8233d05b4f89bc27dcc91e54976d-01-00-01 | ProjectController-queryProject | 447 | 10.0.1.151 | 127.0.0.1 | null?projectCode=JK17071117169419&_=1500344406438 | HTTP | GET | 200 | | | | | | ,2017-07-18-10-20-12-221 | CAMEL-PROJECT | PROJECT-SERVICE | SUCCESS | ee45d36281a24e77bc21dc3856253bb0-02-01-02 | ProjectApiService-getListByQuery | 14 | 172.18.0.62 | 10.0.1.151 | | TCP | | | | | | | [{\"projectCode\":\"JK17071117169419\"}]";
        String[] strs = s.split(reg);
        System.out.println(strs.length);
        for (int i=0; i<strs.length;i++){
            if (i>0) {
                System.out.println(date+strs[i]);
            }else{
                System.out.println(strs[i]);
            }
        }
    }
}
