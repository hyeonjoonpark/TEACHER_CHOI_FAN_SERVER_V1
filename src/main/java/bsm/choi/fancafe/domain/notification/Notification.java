package bsm.choi.fancafe.domain.notification;

import bsm.choi.fancafe.domain.notification.types.ReadStatus;
import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String message;

    @Enumerated(EnumType.STRING)
    private ReadStatus isRead;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Notification(User sender, User receiver, String message, ReadStatus isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.isRead = ReadStatus.NOT_READ;
    }
}
