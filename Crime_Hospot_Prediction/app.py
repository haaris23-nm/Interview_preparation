import streamlit as st
import pandas as pd
import joblib
import os
import folium
from streamlit_folium import st_folium

# Path setup
BASE_DIR = os.path.dirname(os.path.abspath(__file__))

reg_model = joblib.load(os.path.join(BASE_DIR, "model/reg_model.pkl"))
clf_model = joblib.load(os.path.join(BASE_DIR, "model/clf_model.pkl"))

st.title("🚔 Crime Hotspot Prediction System")

# Inputs
area = st.number_input("Community Area", 1, 100)
month = st.slider("Month", 1, 12)

if st.button("Predict"):
    df = pd.DataFrame([[area, month]], columns=["community_area", "month"])

    crime_pred = reg_model.predict(df)[0]
    risk_pred = clf_model.predict(df)[0]

    risk_map = {0: "Low", 1: "Medium", 2: "High"}

    st.success(f"Predicted Crimes: {int(crime_pred)}")
    st.warning(f"Risk Level: {risk_map[risk_pred]}")

# Map
try:
    data = pd.read_csv("data/crime_data.csv")
    data = data.dropna(subset=["latitude", "longitude"])

    m = folium.Map(location=[41.8781, -87.6298], zoom_start=10)

    for _, row in data.sample(300).iterrows():
        folium.CircleMarker(
            location=[row["latitude"], row["longitude"]],
            radius=2,
            color="red",
            fill=True
        ).add_to(m)

    st.subheader("📍 Crime Map")
    st_folium(m, width=700, height=500)

except:
    st.info("Run main.py first")