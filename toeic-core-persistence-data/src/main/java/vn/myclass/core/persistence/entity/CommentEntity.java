package vn.myclass.core.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @Column(name = "createddate")
    private String createdDate;

    @ManyToOne
    @JoinColumn(name= "userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name= "listenguideline")
    private ListenGuidelineEntity listenGuidelineEntity;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenGuidelineEntity getListenGuidelineEntityId() {
        return listenGuidelineEntity;
    }

    public void setListenGuidelineEntityId(ListenGuidelineEntity listenGuidelineEntityId) {
        this.listenGuidelineEntity = listenGuidelineEntityId;
    }
}
