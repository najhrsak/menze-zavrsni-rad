package hr.tvz.zr.menzastudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResponseRequest {
    private HttpStatus status;
    private Long timestamp;
    private Object response;

    public ResponseRequest(){
        this.status = HttpStatus.CREATED;
        this.timestamp = System.currentTimeMillis();
    }
}
