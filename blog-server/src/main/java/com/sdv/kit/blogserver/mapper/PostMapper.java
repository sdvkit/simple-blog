package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.PostCreationDto;
import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post toEntity(PostDto postDto);

    PostDto toDto(Post post);

    Post toEntity(PostCreationDto postCreationDto);

    PostCreationDto toCreationDto(Post post);
}