package me.timur.touroperatorbpa.util;

/**
 * Created by Temurbek Ismoilov on 07/04/25.
 */

public class CommentUtil {
    public static String createComment(String comment, String author) {
        if(comment == null || comment.isEmpty())
            return null;

        return String.format("%s (%s): %s", author, LocalDateTimeUtil.toDay(), comment);
    }

    public static String appendComment(String oldComment, String newComment, String author) {
        if(newComment == null || newComment.isEmpty())
            return null;

        if(oldComment == null || oldComment.isEmpty())
            return createComment(newComment, author);

        return String.format("%s\n\n%s (%s): %s", oldComment, author, LocalDateTimeUtil.toDay(), newComment);
    }
}
