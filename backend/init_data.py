from pymongo import MongoClient
from datetime import datetime

# MongoDB Atlas connection
MONGO_URI = "mongodb+srv://hari:hari919597@cluster1.p2aip1m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster1"
client = MongoClient(MONGO_URI)
db = client.subscription_manager

def init_sample_plans():
    plans_collection = db.subscription_plans
    
    sample_plans = [
        {
            "name": "Basic",
            "description": "Perfect for individuals getting started",
            "price": 9.99,
            "interval": "monthly",
            "features": ["5 Projects", "10GB Storage", "Email Support"],
            "isPopular": False,
            "maxUsers": 1,
            "storage": "10GB",
            "support": "Email",
            "createdAt": datetime.utcnow(),
            "updatedAt": datetime.utcnow()
        },
        {
            "name": "Pro",
            "description": "Best for growing teams and businesses",
            "price": 29.99,
            "interval": "monthly",
            "features": ["Unlimited Projects", "100GB Storage", "Priority Support", "Advanced Analytics"],
            "isPopular": True,
            "maxUsers": 10,
            "storage": "100GB",
            "support": "Priority",
            "createdAt": datetime.utcnow(),
            "updatedAt": datetime.utcnow()
        },
        {
            "name": "Enterprise",
            "description": "For large organizations with advanced needs",
            "price": 99.99,
            "interval": "monthly",
            "features": ["Unlimited Everything", "1TB Storage", "24/7 Support", "Custom Integrations", "Dedicated Manager"],
            "isPopular": False,
            "maxUsers": None,
            "storage": "1TB",
            "support": "24/7",
            "createdAt": datetime.utcnow().isoformat(),
            "updatedAt": datetime.utcnow().isoformat()
        }
    ]
    
    # Clear existing plans
    plans_collection.delete_many({})
    
    # Insert sample plans
    result = plans_collection.insert_many(sample_plans)
    print(f"Inserted {len(result.inserted_ids)} sample plans")

if __name__ == "__main__":
    print("Initializing sample data...")
    init_sample_plans()
    print("Sample data initialization complete!")