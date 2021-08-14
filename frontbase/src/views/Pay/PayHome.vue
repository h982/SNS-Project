<template>
  <div>
    <div data-aos="fade-down">
      <br>
      <h1>프리미엄 서비스</h1>
    </div>

    <div data-aos="fade-right"></div>
    <div class="imp-home">
      <div>
        <v-btn @click="goPay()">
          <a-icon type="credit-card" />
          결제
        </v-btn>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';

export default {
  
  computed: {
    ...mapGetters(["memberInfo"]),
  },
  
  
  methods: {
    goPay(){
      if(!this.memberInfo.authenticated){
        this.$router.push("/payment");
      }else{
        alert("이미 프리미엄 회원입니다.")
      }
    }
  }
};
</script>

<style>


  .nav-container {
    display: flex;
    align-items: center;
    justify-content: center;
    position: absolute;
    top: -75px;
    left: 0;
    right: 0;
    
  }


</style>
