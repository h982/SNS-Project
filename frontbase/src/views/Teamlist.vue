<template>
  <v-container grid-list-xl>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-flex v-for="portfolio in portfolios" :key="portfolio.src" xs12 sm6 md4 lg3 xl3>
        <v-card
          :to="'/portfolio/'+(portfolio.title).toLowerCase()"
          hover
          flat
          color="transparent"
          height="230"
        >
          <v-card-title></v-card-title>
          <v-img :src="portfolio.src" aspect-ratio="2.75" height="130" contain></v-img>
          <v-card-title primary-title class="justify-center">
            <v-flex text-xs-center subheading font-weight-bold>{{portfolio.title}}</v-flex>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
    <v-btn
      color="secondary"
      elevation="7"
      fab
      large
      x-large
      x-small
      class="create"
    ><i class="fas fa-plus"></i>
    </v-btn>
  </v-container>
</template>

<script>
export default {
  metaInfo: {
    title: "Portfolio",
    titleTemplate: "%s ← Eldin's Space",
    meta: [
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      {
        name: "description",
        content:
          "Eldin Zaimovic's Portfolio Web Development Video Editing Graphic Design Front-End Advetising"
      },
      { charset: "utf-8" },
      { property: "og:title", content: "Eldin' Space" },
      { property: "og:site_name", content: "Eldin' Space" },
      { property: "og:type", content: "website" },
      { property: "og:url", content: "https://eldin.space" },
      {
        property: "og:image",
        content: "https://i.imgur.com/Dcz2PGx.jpg"
      },
      {
        property: "og:description",
        content:
          "Eldin Zaimovic's Portfolio Web Development Video Editing Graphic Design Front-End Advetising"
      }
    ]
  },
  data() {
    return {
      portfolios: [
        {
          src: "https://i.imgur.com/GvTt5GG.png",
          title: "러닝왕",
        },
        {
          src: "https://i.imgur.com/wXLtLKy.png",
          title: "헬스왕"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "수영왕"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "탁구왕"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "말아톤"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "초원이"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "다리는"
        },
        {
          src: "https://i.imgur.com/g74mGuU.png",
          title: "백만볼짜리"
        },
      ]
    };
  }
};
</script>

<style scoped>
.create {
  position: absolute;
  right: 0px;
  bottom: 0px;
};
</style>