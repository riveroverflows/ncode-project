package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.domain.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class FeedServiceTest {

    private FeedService feedService;

    @Spy
    private FeedRepository feedRepository;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        feedService = new FeedService(feedRepository);
    }

    @Test
    public void getFeeds() {
        Feed feed = Feed.builder()
                .mdName("엔코드")
                .contents("프로젝트")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build();

        feedRepository.save(feed);

        List<Feed> feeds = feedService.getFeeds();

        assertThat(feeds).hasSize(1);

        assertThat(feeds.get(0).getMdName()).isEqualTo("엔코드");

        verify(feedRepository).findAll();
    }

}