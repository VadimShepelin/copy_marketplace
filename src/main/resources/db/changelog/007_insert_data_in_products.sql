--liquibase formatted sql

--changeset Vadim:1
INSERT INTO products (id, name, description, price, quantity, sku, created_at, quantity_update, category)
VALUES
            ('e1f47ac2-5b6a-42fb-9a41-8f0f9f6539d1', 'Organic Apple', 'Fresh organic apples', 149.99, 300, 'FOOD-APP-001', now(), now(), 'FOOD'),
            ('a8f1f3cb-4739-4c57-8f59-2f78b33d4f34', 'Shampoo X', 'Herbal anti-dandruff shampoo', 249.99, 150, 'COS-SHAM-002', now(), now(), 'COSMETICS'),
            ('1b8df0d9-37cb-4518-a8f7-2f1f0c93a5d6', 'Men’s Jacket', 'Winter collection jacket', 799.99, 100, 'CLO-JAC-003', now(), now(), 'CLOTH'),
            ('d39f7ad4-2f5e-4a1c-8f5b-7f93f2a9c3b8', 'Pet Food Bowl', 'Stainless steel pet food bowl', 119.99, 200, 'PET-BWL-004', now(), now(), 'PET_SUPPLIES'),
            ('f5a9d2e8-3f1b-42c8-a8f3-5b2f7f93a1c9', 'Car Wax Pro', 'Protective car wax', 329.99, 80, 'AUT-WAX-005', now(), now(), 'AUTOMOTIVE_PRODUCTS'),
            ('b6f3c8d1-9a7e-45fb-8f7f-9a3d5f2b1c8a', 'Dog Collar', 'Adjustable dog collar', 892.99, 250, 'PET-COL-006', now(), now(), 'PET_SUPPLIES'),
            ('9f7f2d8a-4c3b-4f5e-8f1b-5a2f3a9c8d7e', 'Sunscreen Lotion', 'SPF 50 waterproof lotion', 179.99, 120, 'COS-SUN-007', now(), now(), 'COSMETICS'),
            ('2d8a5f9f-3b4c-4f1b-8f7f-7a9c5a2f3d1e', 'Instant Noodles', 'Spicy chicken flavored noodles', 129.99, 500, 'FOOD-NDL-008', now(), now(), 'FOOD'),
            ('8f1b9a7c-4f3b-4d2f-8f5e-7f9c3a5a1d2f', 'Leather Shoes', 'Formal leather shoes', 999.99, 60, 'CLO-SHO-009', now(), now(), 'CLOTH'),
            ('5f9a3d2f-8f1b-4c7f-8f5e-3a1d7c5a2f9b', 'Engine Oil X', 'Premium engine oil', 649.99, 90, 'AUT-OIL-010', now(), now(), 'AUTOMOTIVE_PRODUCTS'),
            ('4f8b3a2d-5f9f-4c7e-8f1b-9a7c5d3a2f1d', 'Cat Toy Mouse', 'Interactive cat toy', 792.99, 300, 'PET-TOY-011', now(), now(), 'PET_SUPPLIES'),
            ('8f5e4c3b-9a7d-4f1b-8f2f-7a3d5a1c7f9b', 'Face Wash', 'Charcoal face wash', 159.99, 180, 'COS-WASH-012', now(), now(), 'COSMETICS'),
            ('7f3a5c2d-8f1b-4f9a-8f5e-9a7c4d3a5f1b', 'Cotton T-shirt', 'Plain white t-shirt', 299.99, 220, 'CLO-TSH-013', now(), now(), 'CLOTH'),
            ('8f7c9a5d-3f1b-4a2f-8f5e-7a1d5c4f3a9b', 'Chocolate Bar', 'Dark chocolate with hazelnuts', 149.99, 400, 'FOOD-CHO-014', now(), now(), 'FOOD'),
            ('2d5f7a9c-8f1b-4f3b-8f5e-7a9a3d4c5f1b', 'Conditioner Y', 'Silky hair conditioner', 209.99, 160, 'COS-COND-015', now(), now(), 'COSMETICS'),
            ('8f9a7c5d-4f3b-4f1b-8f5e-3a2d7a5c9a1d', 'Car Air Freshener', 'Lavender scented air freshener', 929.99, 140, 'AUT-FRESH-016', now(), now(), 'AUTOMOTIVE_PRODUCTS'),
            ('4f3a2d7a-5f9c-8f1b-4f7e-9a7a5d3c1f9b', 'Leather Belt', 'Genuine leather belt', 449.99, 110, 'CLO-BEL-017', now(), now(), 'CLOTH'),
            ('3a5d7a9c-8f1b-4f2f-8f5e-9a7f4c3a1d5b', 'Pet Shampoo', 'Anti-flea shampoo for pets', 189.99, 130, 'PET-SHAM-018', now(), now(), 'PET_SUPPLIES'),
            ('9a7a5d3f-8f1b-4c7e-8f5e-4f3a2d7f9a5b', 'Energy Drink', 'Sugar-free energy drink', 199.99, 350, 'FMC-DRINK-019', now(), now(), 'FMCG'),
            ('4f3a5d7a-8f1b-4c9f-8f5e-7a2d9a7f3a5b', 'Toothpaste Max', 'Fluoride toothpaste', 139.99, 210, 'FMC-PASTE-020', now(), now(), 'FMCG');
