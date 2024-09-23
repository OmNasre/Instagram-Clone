# Instagram-Clone
Instagram clone using the SpringBoot and HTMl, CSS and the Hibernate. The database is SQL

## Contributors
    Mohnish Sasane
    Isha Waghaye
    Vaishnavi Hepat
    Saksham Donge
    Nidhi Bhoyar
    Saurabh Jagare
    Vidhan Deshpande

## Introduction:
The Instagram Clone project is built using Spring Boot for the backend and React for the frontend. It replicates core functionalities of Instagram, such as user registration, post 
management, likes, comments, and an admin panel for user and post management. The key feature of this application is a responsive and minimalist design that mimics Instagram’s user experience.

# Key Features:
# User Registration and Login:

Users can register and log in using their credentials. The application supports role-based authentication where Admins are redirected to an admin dashboard, while Regular Users are redirected to the posts page.
The session management ensures users stay logged in, with sessions holding user information.

![RegisterPage](https://github.com/user-attachments/assets/93624245-478f-43da-af81-98fd7a5a136d)

![LoginPage](https://github.com/user-attachments/assets/ba6f511c-66c2-41c3-91fb-c585ba0073e6)

# Post Management:

Users can add posts that are displayed in reverse chronological order on the posts page. A post consists of an image, caption, and the number of likes and comments.
Admins have access to a page where they can view and delete posts.

![HomeScreen](https://github.com/user-attachments/assets/544d30da-9916-4676-911e-364f257cbc14)

# Comments System:

Users can comment on posts. The comments are associated with the logged-in user and the respective post.
The system allows users to edit and delete their comments.

# Likes Feature:

Users can like or unlike a post. The number of likes for each post is updated dynamically.
This feature provides visual feedback to users on the number of likes each post has received.

![CommentAndLike](https://github.com/user-attachments/assets/e19b3b20-5f15-4776-b977-2f227e909ae5)

# Admin Panel:

Admin users have access to a dedicated admin panel where they can manage users and posts.
Admins can view all registered users and posts and have the ability to delete any user or post.

![AdminDashboard](https://github.com/user-attachments/assets/0b77572b-9469-4511-a135-d719e8421184)

# Profile Page:

Each user has a profile page where they can view their posts and comments.
The profile page fetches all the posts created by the user and displays them in a personalized layout.

![ProfilePage](https://github.com/user-attachments/assets/8f98f944-840f-449d-8d7d-8153c353a894)

# Add Post And Delete Post

![AddPost](https://github.com/user-attachments/assets/a25ade94-a650-49e3-867e-b60cfd5e6c41)

![DeletePost](https://github.com/user-attachments/assets/c7bac849-9ae2-4f75-ab2d-a17cc36e8e77)


# Database Design:
User Table:

Stores user details such as userId, username, password, and role.
Post Table:

Stores post information including postId, userId, image, and caption.
Comment Table:

Stores comments related to posts with commentId, postId, userId, and text.
Like Table:

Stores likes associated with posts and users, with likeId, postId, and userId.

## Conclusion:

This Instagram clone project demonstrates key functionalities of a social media platform, including user authentication, post management, comments, likes, and role-based access control. The use of Spring Boot for the backend and React for the frontend provides a scalable solution that can be extended with more features such as notifications, direct messaging, and search functionalities.


