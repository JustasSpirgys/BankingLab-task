<script setup>
    defineProps({
        header: String,
        currencies: Array
    })
</script>

<template>
    <div className="vertical-div">
        <div>
            <span>{{ header }}</span>
        </div>
        <select name="cars" id="cars" className="input">
            <option v-for="(currency, index) in currencies" :value="currency.value" :key="index">{{ currency.name }}</option>
        </select>
    </div>
</template>
  