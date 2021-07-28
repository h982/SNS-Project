<template>
  <v-flex xs12 sm6 md4 lg4 xl4>

    <v-card :to="'/teammain/'" hover>
      <v-img :src="imgPath" aspect-ratio="2.75" height="230" :alt="title"></v-img>
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">{{name}}</h3>
          <div>
            <p class="green--text font-weight-medium">{{introduction}}</p>
          </div>
        </div>
      </v-card-title>
    </v-card>

  </v-flex>
</template>

<script>
export default {
  props: {
    name: {
      type: String,
      required: true
    },
    introduction:{
      type:String,
      required:true
    },
    imgPath: {
      type: String,
      required: true
    },
    teamId: {
      type: String,
      required: true
    }
  }
};
</script>

<style scoped>
</style>