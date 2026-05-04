package com.example.crypto_app_socket.domain.uimodel

data class CoinUiModel(
    val id: String,
    val shortName: String,
    val longName: String,
    val image: String,
    val price: Double,
    val changePercent: Double,
    val isUp: Boolean,
    val lastUpdated : String ? =null
) {
    companion object {
        val empty = CoinUiModel(
            id = "",
            shortName = "",
            longName = "",
            image = "",
            price = 0.0,
            changePercent = 0.0,
            isUp = true
        )
        val mockCoins = listOf(
            CoinUiModel(
                id = "BTC",
                shortName = "BTC",
                longName = "Bitcoin",
                image = "https://cryptologos.cc/logos/bitcoin-btc-logo.png",
                price = 65432.12,
                changePercent = 2.45,
                isUp = true
            ),
            CoinUiModel(
                id = "ETH",
                shortName = "ETH",
                longName = "Ethereum",
                image = "https://cryptologos.cc/logos/ethereum-eth-logo.png",
                price = 3450.67,
                changePercent = 1.23,
                isUp = false
            ),
            CoinUiModel(
                id = "BNB",
                shortName = "BNB",
                longName = "Binance Coin",
                image = "https://cryptologos.cc/logos/bnb-bnb-logo.png",
                price = 567.89,
                changePercent = 0.87,
                isUp = true
            ),
            CoinUiModel(
                id = "SOL",
                shortName = "SOL",
                longName = "Solana",
                image = "https://cryptologos.cc/logos/solana-sol-logo.png",
                price = 178.45,
                changePercent = 3.12,
                isUp = false
            ),
            CoinUiModel(
                id = "XRP",
                shortName = "XRP",
                longName = "Ripple",
                image = "https://cryptologos.cc/logos/xrp-xrp-logo.png",
                price = 0.62,
                changePercent = 1.05,
                isUp = true
            )
        )
    }
}