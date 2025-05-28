package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.model.Comment;
import hrynowieckip.ecommercewebsite.domain.dto.AddCommentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentConverterTest {
    private CommentConverter commentConverter;

    @BeforeEach
    public void setup() {
        commentConverter = new CommentConverter();
    }

    @Test
    public void fromTest() {
        //Given
        Comment expected = Comment.builder()
                .author("Author")
                .comment("Comment test")
                .build();

        AddCommentCommand addCommentCommand = AddCommentCommand.builder()
                .author("Author")
                .comment("Comment test")
                .build();
        //When
        Comment result = commentConverter.from(addCommentCommand);
        //Then
        assertEquals(expected, result);
    }

}