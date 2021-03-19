package hrynowieckip.ecommercewebsite.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCommentCommand {
    private String author;
    private String comment;
    private String productName;
}
