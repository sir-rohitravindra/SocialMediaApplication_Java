public class PostsFactory {

    public PostsFactory() {
        System.out.println("Posts Factory Setup!");
    }

    public Post getPost(String type, String title, User user, String content) {
        Post newPost = null;
        switch (type) {
            case "Image_Post": {
                newPost = new ImagePost(title, user);
                break;

            }
            case "Text_Post": {
                newPost = new TextPost(title, user);
                break;
            }
            default: {
                System.err.println("Incorrect Post Type!");
                // return null;

            }
        }
        try {
            newPost.setPostContent(content);
            newPost.buildpost();
        } catch (Exception e) {

            System.out.println("PostsFactory.getPost Failed!");
        }

        return newPost;
    }

}
