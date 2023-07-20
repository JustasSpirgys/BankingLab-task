<script setup>
import Dropdown from '../components/Dropdown.vue'
import '../styles/App.css';
import axios from 'axios';
</script>

<template>
    <div className="main-div">
        <div className="inline-div">
            <div className="vertical-div">
                <div>
                    <span className="text">Amount</span>
                </div>
                <input v-model="amount" v-bind:id="amount" type="number" min="0" step="0.01" oninput="validity.valid||(value='');" className="input">
            </div>
            <Dropdown v-on:currencyChanged="changeCurrency" name="From" :currencies="currencyList"/>
            <Dropdown v-on:currencyChanged="changeCurrency" name="To" :currencies="currencyList"/>
        </div>
        <div className="inline-div">
            <span className="text">{{convertedAmountString}}</span>
        </div>
        <div className="inline-div">
            <button v-on:click="convertCurrencies" className="button">Convert</button>
        </div>
        
    </div>
</template>

<script>
export default {
    data() {
        return {
            amount: 0,
            convertedAmountString: '0 EUR = 0 EUR',
            fromCurrency: '',
            toCurrency: '',
            currencyList: []
        }
    },
    methods: {
        changeCurrency(name, currency) {
            if (name == "From") {
                this.fromCurrency = currency
            } else {
                this.toCurrency = currency
            }
        },
        convertCurrencies() {
            axios.post('http://localhost:8080/api/convertCurrencies',{
                amount: this.amount,
                fromCurrency: this.fromCurrency,
                toCurrency: this.toCurrency
            })
            .then(response => {this.convertedAmountString = response.data})
        }
    },
    mounted() {
        axios.get('http://localhost:8080/api/getCurrencyList')
        .then(response => {this.currencyList = response.data})
    }
}
</script>