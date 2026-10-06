# Forex Robot — Signal Bot
**By Amin Agha Hamidi**

## د APK جوړولو طریقه (GitHub Actions)

1. دغه project GitHub کې upload کړه
2. **Actions** tab ته ولاړ شه
3. **Build Forex Robot APK** → **Run workflow** کلیک وکړه
4. APK به **Artifacts** کې موجود وي

## د Binance API Integration

| Symbol | Data Source |
|--------|-------------|
| BTCUSD.m | 🟢 Binance WebSocket (real-time) |
| XAUUSD.m | 🟢 Binance PAXG/USDT (approximate gold) |
| JP225.std | 🔵 Smart simulation |

## د App د کارولو طریقه

1. App پرانیزه
2. Password: `Hamidi2026@#AK`
3. App به Binance سره وصل شي
4. MACD سیګنالونه به ښکاره شي

## د MACD سیګنالونه

- **BUY** → MACD د Signal Line نه پورته تیریږي
- **SELL** → MACD د Signal Line نه لاندې تیریږي  
- **Take Profit** → 1:3 ratio
- **Stop Loss** → د ATR (14) پر اساس
