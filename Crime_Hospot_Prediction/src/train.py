import pandas as pd
import os
import joblib
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor, RandomForestClassifier

def train():
    print("📥 Loading processed data...")

    # Path
    BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    file_path = os.path.join(BASE_DIR, "data", "processed_data.csv")

    print("📂 Reading from:", file_path)

    df = pd.read_csv(file_path)

    print("📊 Columns:", df.columns.tolist())

    # Features
    X = df[["community_area", "month"]]

    # Targets
    y_reg = df["crime_count"]
    y_clf = df["risk_level"]

    # ✅ Split once for both
    X_train, X_test, y_reg_train, y_reg_test, y_clf_train, y_clf_test = train_test_split(
        X, y_reg, y_clf, test_size=0.2, random_state=42
    )

    # Regression
    print("🤖 Training Regression Model...")
    reg_model = RandomForestRegressor(n_estimators=100, random_state=42)
    reg_model.fit(X_train, y_reg_train)

    # Classification
    print("🤖 Training Classification Model...")
    clf_model = RandomForestClassifier(n_estimators=100, random_state=42)
    clf_model.fit(X_train, y_clf_train)

    # Save models
    print("💾 Saving models...")
    model_dir = os.path.join(BASE_DIR, "model")
    os.makedirs(model_dir, exist_ok=True)

    joblib.dump(reg_model, os.path.join(model_dir, "reg_model.pkl"))
    joblib.dump(clf_model, os.path.join(model_dir, "clf_model.pkl"))

    print("✅ Models trained and saved successfully!")


if __name__ == "__main__":
    train()