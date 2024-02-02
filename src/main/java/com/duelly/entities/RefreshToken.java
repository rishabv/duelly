package com.duelly.entities;

import jakarta.persistence.*;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

import java.time.Instant;

@Entity
@Table(name="refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken extends BaseEntity {

    private String refreshToken;
    private Instant expiry;

    @OneToOne
    private User user;
}
