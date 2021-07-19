<template>
    <div>
        <h1>비밀번호 변경</h1>
        <div class="form-wrap">
            <div class="input-with-label">
                <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호를 입력하세요." />
                <label for="password">새로운 비밀번호</label>
                <div class="error-text" v-if="error.password">{{error.password}}</div>
            </div>

            <div class="input-with-label">
                <input
                    v-model="passwordConfirm"
                    :type="passwordConfirmType"
                    id="password-confirm"
                    placeholder="비밀번호를 다시한번 입력하세요."
                />
                <label for="password-confirm">비밀번호 확인</label>
                <div class="error-text" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
            </div>
        </div>

        <button 
            class="btn-bottom" 
            @click="onModify" 
            :disabled="!isSubmit" 
            :class="{disabled : !isSubmit}">저장</button>
    </div>
</template>

<script>
import PV from "password-validator";

export default {
    created() {
    this.component = this;

    this.passwordSchema
    .is()
    .min(8)
    .is()
    .max(100)
    .has()
    .digits()
    .has()
    .letters();
    },
    watch: {
            password: function(v) {
                this.checkForm();
            },
            
            passwordConfirm: function(v){
                this.checkForm();
            }
    },
    methods: {
        checkForm() {
            if (
                this.password.length >= 0 &&
                !this.passwordSchema.validate(this.password)
            )
                this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
            else this.error.password = false;

            if (this.password !== this.passwordConfirm)
                this.error.passwordConfirm = "비밀번호가 다릅니다.";
            else this.error.passwordConfirm = false;


            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        onModify() {
            if (this.isSubmit) {
                alert("변경이 완료되었습니다.");
                this.$router.push("/main");
            }
        }
    },
    data: () => {
        return {
            password: "",
            passwordConfirm: "",
            passwordSchema: new PV(),
            isLoading: false,
            error: {
                password: false,
                passwordConfirm: false,
            },
            isSubmit: false,
            passwordType: "password",
            passwordConfirmType: "password",
        };
    }
};
</script>