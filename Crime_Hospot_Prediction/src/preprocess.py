import pandas as pd
import os

def preprocess():
    print("📥 Loading raw data...")
    df = pd.read_csv("data/crime_data.csv")

    print("🧹 Cleaning data...")

    required_cols = ["date", "latitude", "longitude", "community_area"]
    df = df[required_cols]

    df = df.dropna()

    df["date"] = pd.to_datetime(df["date"], errors="coerce")
    df = df.dropna(subset=["date"])

    df["month"] = df["date"].dt.month

    print("📊 Aggregating data...")

    agg = df.groupby(["community_area", "month"]).size().reset_index(name="crime_count")

    def risk(x):
        if x <= 10:
            return 0
        elif x <= 30:
            return 1
        else:
            return 2

    agg["risk_level"] = agg["crime_count"].apply(risk)

    os.makedirs("data", exist_ok=True)
    agg.to_csv("data/processed_data.csv", index=False)

    print("✅ Preprocessing done")
    print("Columns:", agg.columns.tolist())

if __name__ == "__main__":
    preprocess()