package alexsgrails

import com.google.gson.Gson
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


class GetWeiboController {

    private static String AUTHORIZE_URL = "https://api.weibo.com/oauth2/access_token";
    private static String CLIENT_ID = "1011069446";
    private static String REDIRECT_URI = "http://apps.weibo.com/oneeggoneear ";
    private static String CLIENT_SECRET = "1ca11c2dda814b1c55cd08046680a4be";
    private static String CODE = "c99e4bdcb8fbb278686f5344eac641d2";
    private static String TOKEN = "2.00xDrAmBi227GB9525ee8c9bb84p_C";
    private static String UID = "1625600937";
    private static String TIME_LINE_URL = "https://api.weibo.com/2/statuses/public_timeline.json";


    private
    static String GET_CODE = " https://api.weibo.com/oauth2/authorize?client_id=1011069446&redirect_uri=http://apps.weibo.com/oneeggoneear&response_type=code"

    def getWeibo = {
        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(TIME_LINE_URL + "?access_token=" + TOKEN + "&count=5");

        httpClient.executeMethod(method);

        String responseBody = method.getResponseBodyAsString();

        JSONObject jsonObject = JSONObject.fromObject(responseBody);

        JSONArray statusesArr = jsonObject.getJSONArray("statuses");
        List<Content> contents = new ArrayList<Content>();
        for (int i = 0; i < statusesArr.size(); i++) {
            Content content = new Content();
            JSONObject statusesObj = statusesArr.getJSONObject(i);
            content.setText(statusesObj.getString("text"));
            content.setRePostsCount(statusesObj.getString("reposts_count"));
            content.setCommentsCount(statusesObj.getString("comments_count"));

            JSONObject userObj = statusesObj.getJSONObject("user");
            content.setUserScreenName(userObj.getString("screen_name"));
            content.setUserImg(userObj.getString("profile_image_url"));
            content.setUserFollowerCount(userObj.getString("followers_count"));
            content.setUserFriendsCount(userObj.getString("friends_count"));
            content.setUserStatusesCount(userObj.getString("statuses_count"));

            contents.add(content);
        }
        System.out.println(contents.size() + " here is size");
        return ['haha':haha,'contents':contents]
    }

    private void authorizePost() {

        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(AUTHORIZE_URL);

        method.setParameter("client_id", CLIENT_ID);
        method.setParameter("client_secret", CLIENT_SECRET);
        method.setParameter("grant_type", 'authorization_code');
        method.setParameter("code", CODE);
        method.setParameter("redirect_uri", REDIRECT_URI);

        httpClient.executeMethod(method);

        String responseBody = method.getResponseBodyAsString();
        System.out.println(responseBody);
        //return responseBody;
    }

    private void getTimeLine() {

//        HttpClient httpClient = new HttpClient();
//        GetMethod method = new GetMethod(TIME_LINE_URL + "?access_token=" + TOKEN + "&count=10");
//
//        httpClient.executeMethod(method);
//
//        String responseBody = method.getResponseBodyAsString();
//        System.out.println(responseBody);
//
//        JSONObject jsonObject = JSONObject.fromObject(responseBody);
//
//        JSONArray statusesArr = jsonObject.getJSONArray("statuses");
//        List<Content> contents = new ArrayList<Content>();
//        for (int i = 0; i < statusesArr.size(); i++) {
//            Content content = new Content();
//            JSONObject statusesObj = statusesArr.getJSONObject(i);
//            content.setText(statusesObj.getString("text"));
//            content.setRePostsCount(statusesObj.getString("reposts_count"));
//            content.setCommentsCount(statusesObj.getString("comments_count"));
//
//            contents.add(content);
//        }
//        System.out.println(contents.size() + " here is size");

    }

    private List<Content> convertJson(String jsonStr) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);

        JSONArray statusesArr = jsonObject.getJSONArray("statuses");
        List<Content> contents = new ArrayList<Content>();
        for (int i = 0; i < statusesArr.size(); i++) {
            Content content = new Content();
            JSONObject statusesObj = statusesArr.getJSONObject(i);
            content.setText(statusesObj.getString("text"));
            content.setRePostsCount(statusesObj.getString("reposts_count"));
            content.setCommentsCount(statusesObj.getString("comments_count"));
            String attitudesCount = statusesObj.getString("attitudes_count");

            JSONObject userObj = statusesObj.getJSONObject("user");
            String userScreenName = userObj.getString("screen_name");
            String userImg = userObj.getString("profile_image_url");
            String userFollowerCount = userObj.getString("followers_count");
            String userFriendsCount = userObj.getString("friends_count");
            String userStatusesCount = userObj.getString("statuses_count");

            contents.add(content);
        }
        System.out.println(contents.size() + " here is size");
        return contents;
    }
}
