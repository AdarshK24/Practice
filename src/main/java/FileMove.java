import java.awt.PageAttributes.MediaType;
import java.io.*;
import okhttp3.*;
public class FileMove {
  public static void main(String []args) throws IOException{
    OkHttpClient client = new OkHttpClient().newBuilder()
      .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\n  \"msg\": \"Dear Customer, your claim under Grameen Suraksha (Hospicash ) Scheme is on hold. Kindly contact your KM for further clarification. In case of queries, contact Grameen Koota branch or toll-free number 1800 123 153 153.- Grameen Koota\",\n  \"senderId\": \"GKMFSI\",\n  \"actiontypes\": \"OTP\",\n  \"transId\": \"52352\",\n  \"customerid\": \"100015\",\n  \"msisdn\": \"7899651295\",\n  \"customerName\": \"Sushila\"\n}");
    Request request = new Request.Builder()
      .url("http://monitoringapp.grameenkoota.in:8087/gk/api/GrameenkootaSMS")
      .method("POST", body)
      .addHeader("Authorization", "Basic Z3JhbWVlbmtvb3RhOkdLQCMkOCo5JQ==")
      .addHeader("key", "d2cd3720-3809-454a-b445-0c4e6fb98169")
      .addHeader("Content-Type", "application/json")
      .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
  }
}