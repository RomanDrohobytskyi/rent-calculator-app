/* Default rent prices */
INSERT IGNORE INTO integration_test_rent_calculation_system.rent_price (id, gas, water, electricity, rent, actual)
VALUE (-1, 7.46, 28.13, 1.02, 1800, true);

/* Default messages */
INSERT IGNORE INTO integration_test_rent_calculation_system.payment_message (id, title, description, total_media, water, gas, electricity, total, regards, actual)
VALUE (-1, 'Month - %s', 'Description %s.', 'Media - %s',
      'Water %s - %s = %s,', 'Gas %s - %s = %s,', 'Electricity %s - %s = %s,', 'Total: %s.', 'BR.', true);