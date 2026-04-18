from src.ingestion import fetch_data
from src.preprocess import preprocess
from src.train import train

if __name__ == "__main__":
    print("🚀 Running full pipeline...\n")

    fetch_data()
    preprocess()
    train()

    print("\n✅ FULL PIPELINE COMPLETED")