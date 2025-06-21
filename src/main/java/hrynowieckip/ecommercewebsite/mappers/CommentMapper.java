package hrynowieckip.ecommercewebsite.mappers;

import hrynowieckip.ecommercewebsite.domain.dto.CommentDto;
import hrynowieckip.ecommercewebsite.domain.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface CommentMapper {

//    @Mapping(source = "author", target = "author")
//    @Mapping(source = "comment", target = "comment")
    CommentDto toDto(Comment comment);
}
