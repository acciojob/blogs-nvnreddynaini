package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = Image.builder().description(description).dimensions(dimensions).build();

        Blog blog = blogRepository2.findById(blogId).get();

        image.setBlog(blog);

        //Because of Bi-directional Mapping
        //Updating the blog info and changing its images
        List<Image> currentImages = blog.getImageList();
        currentImages.add(image);
        blog.setImageList(currentImages);


        blogRepository2.save(blog);

        //imageRepository2.save(image);

        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String S = image.getDimensions();
        char ch[] = S.toCharArray();
        int t1 = (ch[0]-'0');
        int t2 = (ch[2]-'0');
        //int total_length = (ch[0]-'0')*(ch[2]-'0');
        String str = screenDimensions;
        char ch1[] = str.toCharArray();
        int l1 = (ch1[0]-'0');
        int l2 = (ch1[2]-'0');
        //int length = (ch1[0]-'0')*(ch1[2]-'0');
        return ((t1/l1)*(t2/l2));
    }
}
