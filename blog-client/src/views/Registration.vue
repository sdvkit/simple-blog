<template>
  <div class="wrapper">
    <Card class="registration-form-card">
      <template #title>
        <h3>Registration</h3>
      </template>
      <template #content>
        <InputText type="text" v-model="username" :style="{ width: '100%' }" placeholder="Username" />
        <InlineMessage v-if="showUsernameError">Length of Username must be more than 5</InlineMessage>
        <br>
        <br>
        <InputText type="text" v-model="email" :style="{ width: '100%' }" placeholder="Email" />
        <InlineMessage v-if="showEmailError">Length of Email must be more than 8</InlineMessage>
        <br>
        <br>
        <InputText v-model="password" :style="{ width: '100%' }" placeholder="Password" />
        <br><br>
        <InputText v-model="confirmPassword" :style="{ width: '100%' }" placeholder="Confirm password" />
        <InlineMessage v-if="showPasswordError">Passwords aren't equals</InlineMessage>
        <br>
        <br>
        <Button label="Create" :style="{ float: 'right' }" @click="createAccount" />
        <router-link to="/login">
          <Button label="Login" class="p-button-text" :style="{ float: 'right' }" />
        </router-link>
      </template>
    </Card>
  </div>
</template>

<script>
import Card from "primevue/card"
import InputText from 'primevue/inputtext'
import Button from "primevue/button"
import InlineMessage from "primevue/inlinemessage"

export default {
  name: "registration-view",
  data() {
    return {
      email: '',
      username: '',
      password: '',
      confirmPassword: '',
      showUsernameError: false,
      showEmailError: false,
      showPasswordError: false
    }
  },
  methods: {
    createAccount() {
      if (this.username.length < 5) {
        this.showUsernameError = true
        return
      }
      this.showUsernameError = false

      if (this.email.length < 8) {
        this.showEmailError = true
        return
      }
      this.showEmailError = false

      if (this.password !== this.confirmPassword || this.password.length === 0) {
        this.showPasswordError = true
        return
      }
      this.showPasswordError = false


    }
  },
  components: {
    Card,
    InputText,
    Button,
    InlineMessage
  }
}
</script>

<style lang="scss" scoped>

  .wrapper {
    padding-top: 20px;
    padding-bottom: 20px;
    background: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='1440' height='560' preserveAspectRatio='none' viewBox='0 0 1440 560'%3e%3cg mask='url(%26quot%3b%23SvgjsMask1000%26quot%3b)' fill='none'%3e%3crect width='1440' height='560' x='0' y='0' fill='%231e3266'%3e%3c/rect%3e%3cpath d='M1512 560L0 560 L0 299.37Q81.38 260.75%2c 120 342.13Q162.17 312.3%2c 192 354.47Q239.95 282.42%2c 312 330.37Q377.34 275.71%2c 432 341.05Q482.94 319.99%2c 504 370.93Q511.83 306.76%2c 576 314.6Q644.84 263.44%2c 696 332.28Q761.62 277.9%2c 816 343.52Q860.36 267.88%2c 936 312.23Q1020.62 276.85%2c 1056 361.46Q1083.36 316.81%2c 1128 344.17Q1144.23 288.4%2c 1200 304.64Q1273.11 257.75%2c 1320 330.86Q1356.18 247.05%2c 1440 283.23Q1489.72 260.96%2c 1512 310.68z' fill='%23182f5d'%3e%3c/path%3e%3cpath d='M1464 560L0 560 L0 353.21Q79.52 360.73%2c 72 440.24Q88.31 336.55%2c 192 352.86Q265.81 306.68%2c 312 380.49Q402.26 350.75%2c 432 441.01Q455.27 344.28%2c 552 367.56Q623.63 319.19%2c 672 390.83Q733.42 380.25%2c 744 441.67Q770.95 396.63%2c 816 423.58Q867.81 355.39%2c 936 407.2Q953.62 352.81%2c 1008 370.43Q1052.66 343.1%2c 1080 387.76Q1145.05 332.81%2c 1200 397.85Q1256.43 382.28%2c 1272 438.71Q1297.9 344.61%2c 1392 370.51Q1440.99 347.5%2c 1464 396.49z' fill='%2325467d'%3e%3c/path%3e%3cpath d='M1488 560L0 560 L0 473.01Q17.88 418.88%2c 72 436.76Q101.89 394.65%2c 144 424.55Q199.64 408.19%2c 216 463.84Q300.44 428.28%2c 336 512.72Q383.5 440.22%2c 456 487.72Q468.2 427.93%2c 528 440.13Q596.54 388.67%2c 648 457.21Q665.8 403%2c 720 420.8Q767.74 396.54%2c 792 444.28Q863.45 443.73%2c 864 515.18Q879.99 459.16%2c 936 475.15Q954.97 422.12%2c 1008 441.08Q1054.48 415.56%2c 1080 462.05Q1138.2 448.25%2c 1152 506.46Q1177.02 459.48%2c 1224 484.5Q1248.19 436.69%2c 1296 460.88Q1337.7 430.58%2c 1368 472.28Q1409.93 394.21%2c 1488 436.15z' fill='%23356cb1'%3e%3c/path%3e%3cpath d='M1464 560L0 560 L0 574Q21.71 523.71%2c 72 545.43Q93.46 494.89%2c 144 516.35Q167.88 468.24%2c 216 492.12Q311.97 468.09%2c 336 564.05Q376.25 484.29%2c 456 524.54Q497.94 494.48%2c 528 536.42Q589.56 525.98%2c 600 587.55Q644 511.55%2c 720 555.55Q740.43 503.98%2c 792 524.41Q819.6 480%2c 864 507.6Q963.01 486.62%2c 984 585.63Q989.25 518.88%2c 1056 524.14Q1121.87 470.01%2c 1176 535.88Q1199.41 487.29%2c 1248 510.7Q1277.36 468.06%2c 1320 497.41Q1370.31 475.72%2c 1392 526.02Q1421.62 483.64%2c 1464 513.26z' fill='white'%3e%3c/path%3e%3c/g%3e%3cdefs%3e%3cmask id='SvgjsMask1000'%3e%3crect width='1440' height='560' fill='white'%3e%3c/rect%3e%3c/mask%3e%3c/defs%3e%3c/svg%3e") center no-repeat;
    background-size: 220vh;
  }

  .registration-form-card {
    display: block;
    max-width: 600px;
    min-height: 410px;
    margin: 50px auto 70px;
    padding-left: 80px;
    padding-right: 80px;
  }

  @media screen and (min-height: 650px) {

    .wrapper {
      margin-top: -15vh;
      height: 100vh;
      padding-top: 20vh;
      background-size: 260vh;
    }
  }
</style>