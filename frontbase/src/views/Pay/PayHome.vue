<template>
  <div>
    <div class="exp-container">
      <br>
      <h1>프리미엄 서비스</h1>
    
    </div>
    <div class="imp-home">
      <div class="nav-container">
        <router-link to="/payment">
          <a-icon type="credit-card" />
          결제
        </router-link>
        
      </div>
    </div>
  </div>
</template>

<style>
.exp-container {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 50%;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;

}

.imp-home {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #fff;
}
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
