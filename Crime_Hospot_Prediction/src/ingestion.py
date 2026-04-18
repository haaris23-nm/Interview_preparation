import requests
import pandas as pd
import os

URL = "https://data.cityofchicago.org/resource/ijzp-q8t2.json"

def fetch_data(limit=5000):
    params = {"$limit": limit}
    response = requests.get(URL, params=params)
    data = response.json()

    df = pd.DataFrame(data)

    os.makedirs("data", exist_ok=True)
    df.to_csv("data/crime_data.csv", index=False)

    print("✅ Data saved")

if __name__ == "__main__":
    fetch_data()