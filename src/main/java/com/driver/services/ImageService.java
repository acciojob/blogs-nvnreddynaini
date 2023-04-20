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
        int xi = 0, yi = 0, xs = 0, ys = 0, num = 0;
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i)=='X'){
                xi = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (S.charAt(i) - '0');
        }
        yi = num;
        num = 0;
        String str = screenDimensions;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i)=='X'){
                xs = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (str.charAt(i) - '0');
        }
        ys = num;

        return ((xs/xi)*(ys/yi));
    }
}
