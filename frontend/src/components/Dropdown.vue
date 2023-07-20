<script setup>
defineProps({
    name: String,
    currencies: Array
})
</script>

<script>
export default {
    data() {
        return {
            currencySelected:'EUR'
        }
    },
    methods: {
        passCurrencyToParent() {
            this.$emit('currencyChanged', this.name, this.currencySelected);
        }
    },
    mounted() {
        this.passCurrencyToParent()
    }
}
</script>

<template>
    <div className="vertical-div">
        <div>
            <span className="text">{{ name }}</span>
        </div>
        <select v-model="currencySelected" v-on:change="passCurrencyToParent" v-bind:name="name" v-bind:id="name" className="input dropdown">
            <option v-for="currency in currencies">{{ currency }}</option>
        </select>
    </div>
</template>
