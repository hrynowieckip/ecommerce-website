package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.model.Comment;
import hrynowieckip.ecommercewebsite.web.command.AddCommentCommand;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public Comment from(AddCommentCommand addCommentCommand) {
        return Comment.builder()
                .author(addCommentCommand.getAuthor())
                .comment(addCommentCommand.getComment())
                .build();
    }
}
