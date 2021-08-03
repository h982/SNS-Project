<template>
  <tr>
    <td>{{ no }}</td>
    <td>{{ title }}</td>
    <td>{{ contents }}</td>
    <td>{{ endDate }}</td>
    <td><v-btn  @click="delete1">등록</v-btn></td>

  </tr>
</template>

<script>
export default {
  name: "ListRow",
  props: {
    no: String,
    title: String,
    contents: String,
    endDate: String,
  },
  data() {
    return {
    
    }
  },
};
</script>
<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 30px;
  width: 70px;
}
</style>
