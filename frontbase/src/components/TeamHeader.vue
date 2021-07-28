<template>
    <div>
        <v-layout >
            <v-bottom-navigation
                class="mx-auto"
                shift
                x-large
            >
        <v-btn color="secondary">
            <i class="fas fa-address-card fa-2x" @click="running"></i>
            <div>&nbsp;&nbsp;&nbsp;정보</div>
        </v-btn>

        <v-btn color="success">
            <i class="fas fa-clipboard fa-2x" @click="helth"></i>
            <div>&nbsp;&nbsp;&nbsp;피드</div>
        </v-btn>

        <v-btn color="primary">
            <i class="fas fa-check fa-2x" @click="swimming"></i>
            <div>&nbsp;&nbsp;&nbsp;공지사항</div>
        </v-btn>
        
        <v-btn color="warning">
            <i class="fas fa-comments fa-2x" @click="tableTennis"></i>
            <div>&nbsp;&nbsp;&nbsp;채팅</div>
        </v-btn>

        <v-btn color="error">
            <i class="fas fa-trophy fa-2x" @click="moveTeamChallenge"></i>
            <div>&nbsp;&nbsp;&nbsp;챌린지</div>
        </v-btn>
        </v-bottom-navigation>
    </v-layout>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
    props: {
        goDark: {
            type: Boolean
        }
    },
    data() {

    },
    computed: {

    },
    methods: {
        moveTeamChallenge(){
            this.$router.push("/teamChallenge");
        },
    },
}
</script>

<style >
</style>