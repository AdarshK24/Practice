import java.io.*;
import okhttp3.*;
public class Main {
  public static void main(String []args) throws IOException{
    OkHttpClient client = new OkHttpClient().newBuilder()
      .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\n    \"customerEnrolmentIns\": {\n        \"applicationId\": \"com.iexceed.gkcustomer\",\n        \"memberId\": \"10000113\",\n        \"subEnrolmentId\": \"\",\n        \"productCode\": \"21059\",\n        \"kendraId\": \"98598\",\n        \"groupId\": \"398863\",\n        \"branchId\": \"IN0010012\",\n        \"memberName\": \"New member edited\",\n        \"coreMemberName\": \"Sharath Kumar\",\n        \"memberDob\": \"19/08/1963\",\n        \"coreMemberDob\": \"15/01/1997\",\n        \"memberAddr1\": \"shdvd\",\n        \"memberAddr2\": \"dhdvd\",\n        \"memberAddrDistrict\": \"vddh\",\n        \"memberAddrCity\": \"sgshs\",\n        \"memberAddrState\": \"svshs\",\n        \"memberAddrPincode\": \"449438\",\n        \"memberGender\": \"M\",\n        \"memberEmailId\": \"\",\n        \"memberMobNum\": \"2536987410\",\n        \"coreMemberMobNum\": \"2536987410\",\n        \"mobNumRel\": \"M\",\n        \"modeOfPayment\": \"C\",\n        \"nameMismatch\": \"N\",\n        \"dobMismatch\": \"N\",\n        \"coMemberExists\": \"N\",\n        \"enrolmentStartDate\": \"\",\n        \"enrolmentEndDate\": \"\",\n        \"enrolmentStatus\": \"\",\n        \"pushbackReason\": \"\",\n        \"rejectReason\": \"\",\n        \"comments\": \"\",\n        \"memberBankAccNum\": \"\",\n        \"memberBankIfsc\": \"\",\n        \"memberPolicyNum\": \"\",\n        \"loanId\": \"\",\n        \"loanCreationDate\": \"\",\n        \"cbStatus\": \"\",\n        \"derivatives\": \"\",\n        \"cbDate\": \"\",\n        \"enrolmentAmount\": 490,\n        \"packageDetail\": \"M+S\",\n        \"createdBy\": \"ABDKM2\",\n        \"userRole\": \"KENDRA_MANAGER\",\n        \"modifiedBy\": \"ABDKM2\",\n        \"memberMismatch\": \"N\",\n        \"createdByName\": \"User_KM_2\",\n        \"maritalStatus\": \"MARRIED\",\n        \"coCustomerEnrolmentIns\": [\n            {\n                \"coMemberName\": \"spouse edited\",\n                \"coMemberGender\": \"M\",\n                \"coMemberDob\": \"19/08/1963\",\n                \"coMemberAddrPincode\": \"495688\",\n                \"coMemberAddrCity\": \"dvdux edited\",\n                \"coMemberAddrDistrict\": \"svdhd edited\",\n                \"coMemberAddrState\": \"svdbd edited\",\n                \"coMemberAddr1\": \"rhxbc\",\n                \"coMemberAddr2\": \"dgshd\",\n                \"nameMismatch\": \"Y\",\n                \"dobMismatch\": \"Y\",\n                \"coMemberMismatch\": \"N\",\n                \"coreCoMemberName\": \"Husenasab Nadaf Late B\",\n                \"coreCoMemberDob\": \"01/01/1950\"\n            }\n        ]\n    }\n}");
    Request request = new Request.Builder()
      .url("http://localhost:8080/InsuranceManagement/wageloss/customerenrolment/save")
      .method("POST", body)
      .addHeader("Content-Type", "application/json")
      .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
  }
}
