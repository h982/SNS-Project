<template>
  <tr>
    <td>{{ no }}</td>
    <td>
      <router-link :to="`book/view?isbn=${isbn}`">{{ title }}</router-link>
    </td>
    <td>{{ author }}</td>
    <td>{{ joindate }}</td>
  </tr>
</template>

<script>
export default {
  name: "listrow",
  props: {
    no: String,
    isbn: String,
    title: String,
    author: String,
    price: Number,
    joindate: String,
  },
  methods: {
    
  }
};
</script>
<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
