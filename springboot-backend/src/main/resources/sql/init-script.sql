/* Default rent prices */
INSERT IGNORE INTO rent_calculation_system.rent_price (id, gas, water, electricity, rent, actual)
VALUE (-1, 1, 1, 1, 1, true);

/* Default messages */
INSERT IGNORE INTO rent_calculation_system.payment_message (id, title, description, total_media, water, gas, electricity, total, regards, actual)
VALUE (-1, 'title', 'description', 'total_m', 'water', 'gas', 'electricity', 'total', 'greetings', true);