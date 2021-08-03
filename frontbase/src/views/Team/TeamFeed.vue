<template>
    <div class="feed newsfeed">
        <v-layout >
            <v-bottom-navigation
                class="mx-auto"
                shift
                x-large
            >
                <team-header />
            </v-bottom-navigation>
        </v-layout>
        <div class="wrapB">
            <feed-item></feed-item>
            <feed-item></feed-item>
            <feed-item></feed-item>
        </div>
    </div>
</template>
<script>
import "@/components/css/feed/feed-item.scss";
import "@/components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";
import TeamHeader from "@/components/TeamHeader.vue"

export default {
    components: {
        FeedItem,
        TeamHeader
    },
    methods: {
    
    },
};
</script>
