package training.javabrains.facebook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import training.javabrains.facebook.data.Comment;
import training.javabrains.facebook.data.Message;
import training.javabrains.facebook.database.DatabaseClass;

public class CommentService {

	private static Map<Long, Message> messageMap = DatabaseClass.getMessageMap(); // Fake database // Fake database

	public CommentService() {
	}

	public List<Comment> getAllComments(long messageId) {

		Map<Long, Comment> commentMap = messageMap	.get(messageId)
													.getComments();
		return new ArrayList<>(commentMap.values());
	}

	public Comment getComment(long messageId, long commentId) {

		Map<Long, Comment> commentMap = messageMap	.get(messageId)
													.getComments();

		return commentMap.get(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {

		Map<Long, Comment> commentMap = messageMap	.get(messageId)
													.getComments();

		comment.setId(commentMap.size() + 1);
		commentMap.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {

		Map<Long, Comment> commentMap = messageMap	.get(messageId)
													.getComments();

		if (comment.getId() <= 0) {
			return null;
		}

		commentMap.put(comment.getId(), comment);
		return comment;

	}

	public Comment removeComment(long messageId, long commentId) {

		Map<Long, Comment> commentMap = messageMap	.get(messageId)
													.getComments();
		return commentMap.remove(commentId);
	}
}
