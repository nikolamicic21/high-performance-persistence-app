package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.secondlevelcache.readonly.ReadOnlyBiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.secondlevelcache.readonly.ReadOnlyBiOneToManyPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.CacheUtil;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.EntityManagerUtil;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.SqlUtil;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    private static EntityManagerFactory emFactory;

    public static void main(String[] args) {
        emFactory = EntityManagerUtil.newEntityManagerFactory();
        emFactory.close();
    }

    private static void executeCacheReadOnlyRoutine() {
        // INSERT
        final var postId = 4L;
        final var postCommentId = 4L;
        SqlUtil.doInTransaction(emFactory, it -> {
            final var post = new ReadOnlyBiOneToManyPost();
            post.setId(postId);
            post.setName("Post1");
            it.persist(post);

            final var comment = new ReadOnlyBiOneToManyPostComment();
            comment.setId(postCommentId);
            comment.setMessage("JDBC part review");
            comment.setPost(post);
            it.persist(comment);
        });
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName() + ".postComments");
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPostComment.class.getName());

        SqlUtil.doInTransaction(emFactory, it -> {
            final var foundPost = it.find(ReadOnlyBiOneToManyPost.class, postId);
        });
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName() + ".postComments");

        // UPDATE
//        SqlUtil.doInTransaction(emFactory, it -> {
//            final var foundPost = it.find(ReadOnlyBiOneToManyPost.class, postId);
//            foundPost.setName(foundPost.getName() + "!");
//            it.persist(foundPost);
//        });
//        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
//        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName() + ".postComments");

        // REMOVE Item from Collection
        SqlUtil.doInTransaction(emFactory, it -> {
            final var foundPost = it.find(ReadOnlyBiOneToManyPost.class, postId);
            final var removedComment = foundPost.getPostComments().remove(0);
            removedComment.setPost(null);
        });
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName() + ".postComments");
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPostComment.class.getName());

        SqlUtil.doInTransaction(emFactory, it -> {
            it.find(ReadOnlyBiOneToManyPost.class, postId);
        });

        // DELETE
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPostComment.class.getName());
        SqlUtil.doInTransaction(emFactory, it -> {
            final var post = it.find(ReadOnlyBiOneToManyPost.class, postId);
            it.remove(post);
        });
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPost.class.getName());
        CacheUtil.printCacheRegionStatistics(emFactory.unwrap(SessionFactory.class), ReadOnlyBiOneToManyPostComment.class.getName());
    }
}
