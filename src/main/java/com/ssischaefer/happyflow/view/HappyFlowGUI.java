package com.ssischaefer.happyflow.view;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ssischaefer.happyflow.controller.ContainerController;
import com.ssischaefer.happyflow.controller.PackageController;
import com.ssischaefer.happyflow.controller.ProductController;
import com.ssischaefer.happyflow.data.Package;
import com.ssischaefer.happyflow.data.Product;
import com.ssischaefer.happyflow.database.OracleDB;
import com.ssischaefer.happyflow.rest.RESTServer;

import swing2swt.layout.BorderLayout;

public class HappyFlowGUI {

	// GUI Components
	// ----------------------------------------------------------------------------------------------------
	// GUI frame
	private Display display;
	private Shell shell;

	// GUI Commposites
	private Composite composite_stack;
	private Composite composite_pallet;
	private Composite composite_pallet_north;
	private Composite composite_pallet_center;
	private Composite composite_pallet_south;
	private Composite composite_package;
	private Composite composite_package_north;
	private Composite composite_package_center;
	private Composite composite_package_south;
	private Composite composite_product;
	private Composite composite_product_north;
	private Composite composite_product_center;
	private Composite composite_product_center_center;
	private Composite composite_product_south;
	private Composite composite_bad_quality;
	private Composite composite_bad_quality_north;
	private Composite composite_bad_quality_center;
	private Composite composite_bad_quality_center_center;
	private Composite composite_bad_quality_south;
	private Composite composite_empty;
	private Composite composite_empty_north;
	private Composite composite_empty_center;
	private Composite composite_empty_south;
	private Composite composite_wrong_product;
	private Composite composite_wrong_product_north;
	private Composite composite_wrong_product_center;
	private Composite composite_wrong_product_center_center;
	private Composite composite_wrong_product_south;
	private Composite composite_insert_package;
	private Composite composite_insert_package_north;
	private Composite composite_insert_package_center;
	private Composite composite_insert_package_south;

	// GUI Layout
	private StackLayout stackLayout;

	// GUI Labels
	private Label lb_pallet_text_decanting;
	private Label lb_pallet_text_pallet;
	private Label lb_pallet_text_scan;
	private Label lb_pallet_icon;
	private Label lb_pallet_button_enter;
	private Label lb_package_text_decanting;
	private Label lb_package_text_pallet;
	private Label lb_package_icon_menu;
	private Label lb_package_text_scan;
	private Label lb_package_icon;
	private Label lb_package_button_enter;
	private Label lb_product_icon_menu;
	private Label lb_product_text_decanting;
	private Label lb_product_text_pallet;
	private Label lb_product_icon_arrow;
	private Label lb_product_text_name;
	private Label lb_product_text_pieces;
	private Label lb_product_icon_product_arrow;
	private Label lb_product_icon_product;
	private Label lb_product_icon_plus;
	private Label lb_product_icon_minus;
	private Label lb_product_icon_container;
	private Label lb_product_text_container;
	private Label lb_product_button_confirm_amount;
	private Label lb_bad_quality_icon_menu;
	private Label lb_bad_quality_text_decanting;
	private Label lb_bad_quality_icon_arrow;
	private Label lb_bad_quality_text_name;
	private Label lb_bad_quality_text_pieces;
	private Label lb_bad_quality_text_pallet;
	private Label lb_bad_quality_icon_bad_quality_arrow;
	private Label lb_bad_quality_text_place_package_in_container;
	private Label lb_bad_quality_text_product;
	private Label lb_bad_quality_text_container;
	private Label lb_bad_quality_icon_container;
	private Label lb_bad_quality_button_send_to_quarantine;
	private Label lb_empty_text_decanting;
	private Label lb_empty_text_pallet;
	private Label lb_empty_text_empty;
	private Label lb_empty_icon;
	private Label lb_empty_button_yes;
	private Label lb_empty_button_no;
	private Label lb_wrong_product_icon_menu;
	private Label lb_wrong_product_text_decanting;
	private Label lb_wrong_product_icon_arrow;
	private Label lb_wrong_product_text_name;
	private Label lb_wrong_product_text_pieces;
	private Label lb_wrong_product_text_pallet;
	private Label lb_wrong_product_text_place_package_in_container;
	private Label lb_wrong_product_text_product;
	private Label lb_wrong_product_icon_wrong_item_arrow;
	private Label lb_wrong_product_text_container;
	private Label lb_wrong_product_icon_container;
	private Label lb_wrong_product_button_send_to_service;
	private Label lb_insert_package_text_decanting;
	private Label lb_insert_package_text_pallet;
	private Label lb_insert_package_icon_menu;
	private Label lb_insert_package_text_insert_a_new_package;
	private Label lb_insert_package_text_packageID;
	private Label lb_insert_package_text_productID;
	private Label lb_insert_product_text_productAmount;
	private Label lb_insert_product_button_confirm;

	// GUI Menus
	private Menu menu_package;
	private Menu menu_product;
	private Menu menu_bad_quality;
	private Menu menu_wrong_product;
	private Menu menu_insert_package;

	//GUI MenuItems
	private MenuItem mntm_package_back;
	private MenuItem mntm_package_exit;
	private MenuItem mntmBadQuality;
	private MenuItem mntmWrongProduct;
	private MenuItem mntmChangeAmount;
	private MenuItem mntm_product_back;
	private MenuItem mntm_product_exit;
	private MenuItem mntm_bad_quality_back;
	private MenuItem mntm_bad_quality_exit;
	private MenuItem mntm_wrong_product_back;
	private MenuItem mntm_wrong_product_exit;
	private MenuItem mntm_insert_package_back;

	// GUI Texts
	private Text txt_pallet_id;
	private Text txt_package_id;
	private Text txt_product_amount;
	private Text txt_insert_package_packageID;
	private Text txt_insert_package_productID;
	private Text txt_insert_package_productAmount;

	// GUI Image Imports
	private final Image pallet_blue = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/pallet_blue.png");
	private final Image pallet_green = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/pallet_green.png");
	private final Image pallet_red = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/pallet_red.png");
	private final Image pallet_grey = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/pallet_grey.png");
	private final Image package_blue = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/package_blue.png");
	private final Image package_green = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/package_green.png");
	private final Image package_red = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/package_red.png");
	private final Image menu = SWTResourceManager
			.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/menu.png");
	// ----------------------------------------------------------------------------------------------------
	
	
	// GUI Relevant Variables
	// ----------------------------------------------------------------------------------------------------
	// Objects
	private Package pa;
	private Product pr;

	// Lists
	private List<String> packageIDs;
	private List<String> palletIDs;

	// Strings
	private String packageID;
	private String palletID;

	// Listeners
	private PaintListener paint;

	// Booleans
	private boolean changeColor;
	private boolean increaseAmount;
	// ----------------------------------------------------------------------------------------------------
	

	// GUI Start Method
	// ----------------------------------------------------------------------------------------------------
	public void openShell() {
		display = Display.getDefault();

		createContents();

		OracleDB.connect(); // Connect to Database
		RESTServer.connect(); // Connect to REST Server

		happyFlow(); // Starts HappyFlow

		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	// ----------------------------------------------------------------------------------------------------

	
	// HappyFlow Start Method
	// ----------------------------------------------------------------------------------------------------
	private void happyFlow() {
		palletIDs = PackageController.getPalletIDs(); // Fill PalletIDs List

		
		// GUI Close Listener
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				OracleDB.disconnect(); // Disconnect to Database
				RESTServer.disconnect(); // Disconnect to REST Server
				System.exit(0);
			}
		});
		
		
		//Enter Button
		// Checks whether the pallet is valid
		lb_pallet_button_enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				palletID = txt_pallet_id.getText();

				if (!palletID.isEmpty()) {
					if (palletIDs.contains(palletID)) {
						packageIDs = PackageController.getPackageIDs(palletID);
						showPalletGreen(palletID);
					} else {
						showPalletRed();
					}
				} else {
					showPalletRed();
				}
			}
		});

		
		// Enter Button
		// Checks whether the package is valid
		lb_package_button_enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				packageID = txt_package_id.getText();

				if (!packageID.isEmpty()) {
					if (packageIDs.contains(packageID)) {
						showPackageGreen(packageID);
					} else {
						showPackageRed(palletID);
					}
				} else {
					showPackageRed(palletID);
				}
			}
		});

		
		// Back MenuItem
		// From package to pallet
		mntm_package_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalletBlue();
			}
		});

		
		// Exit MenuItem
		// From package to pallet
		mntm_package_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalletBlue();
			}
		});

		
		// Plus Label
		// Increase Product Amount
		lb_product_icon_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				int productAmount = pa.getProductAmount();
				int currentProductAmount = Integer.parseInt(txt_product_amount.getText());

				increaseProductAmount(productAmount, currentProductAmount);
			}
		});

		
		// Minus Label
		// Decrease Product Amount
		lb_product_icon_minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				int productAmount = Integer.parseInt(txt_product_amount.getText());

				decreaseProductAmount(productAmount);
			}
		});

		
		// Product Amount Text
		// Change Product Amount
		txt_product_amount.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				int productAmount = pa.getProductAmount();
				Text currentProductAmountText = (Text) e.widget;

				if (!currentProductAmountText.getText().isEmpty()) {
					int currentProductAmount = Integer.parseInt(currentProductAmountText.getText());

					changeProductAmount(productAmount, currentProductAmount);
				} else {
					return;
				}

			}
		});

		
		// Change Amount MenuItem
		// Change Product Amount
		mntmChangeAmount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(shell, "Change amount", "Insert new product amount.", "", null);
				
				if (dialog.open() == Window.OK) {
					changeProductAmount(Integer.parseInt(txt_product_amount.getText()), Integer.parseInt(dialog.getValue()));
			    }
			}
		});

		
		// Confirm Amount Label
		// Confirm Product Amount
		lb_product_button_confirm_amount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				pa = PackageController.getPackage(packageID);

				int packageProductAmount = pa.getProductAmount();
				String currentProductAmountText = txt_product_amount.getText();

				if (!currentProductAmountText.isEmpty()) {
					int currentProductAmount = Integer.parseInt(currentProductAmountText);

					if (packageProductAmount > currentProductAmount) {
						ContainerController.insertProductIntoContainer(pa.getContainerID(), pa.getProductID(),
								currentProductAmount);
						PackageController.updateProductAmount(packageID, packageProductAmount - currentProductAmount);
						packageIDs.remove(packageID);
					} else if (packageProductAmount == currentProductAmount) {
						ContainerController.insertProductIntoContainer(pa.getContainerID(), pa.getProductID(),
								currentProductAmount);
						PackageController.deletePackage(packageID);
						packageIDs.remove(packageID);
					} else if (packageProductAmount < currentProductAmount) {
						ContainerController.insertProductIntoContainer(pa.getContainerID(), pa.getProductID(),
								currentProductAmount);
						PackageController.deletePackage(packageID);
						packageIDs.remove(packageID);
					}
				} else {
					return;
				}

				lb_product_icon_product.removePaintListener(paint);

				if (!packageIDs.isEmpty()) {
					showPackageBlue(palletID);
				} else {
					showEmpty(palletID);
				}
			}
		});

		
		// Plus Button
		// Increase Product Amount
		mntm_product_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPackageBlue(palletID);
			}
		});

		
		// Back MenuItem
		// From package to pallet
		mntm_product_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalletBlue();
			}
		});

		
		// Bad Quality MenuItem
		// From product to bad quality
		mntmBadQuality.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showBadQuality(packageID);
			}
		});

		
		// Bad Quality Send Label
		// Send product to bad quality container
		lb_bad_quality_button_send_to_quarantine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				pa = PackageController.getPackage(packageID);

				int packageProductAmount = pa.getProductAmount();

				ContainerController.insertProductInBadQualityContainer(pa.getProductID(), packageProductAmount);
				PackageController.deletePackage(packageID);
				packageIDs.remove(packageID);

				lb_product_icon_product.removePaintListener(paint);

				if (!packageIDs.isEmpty()) {
					showPackageBlue(palletID);
				} else {
					showEmpty(palletID);
				}
			}
		});

		
		// Back MenuItem
		// From bad quality to product
		mntm_bad_quality_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showProduct(packageID);
			}
		});

		
		// Back MenuItem
		// From bad quality to pallet
		mntm_bad_quality_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalletBlue();
			}
		});

		
		// Wrong Product MenuItem
		// From product to wrong product
		mntmWrongProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showWrongProduct(packageID);
			}
		});

		
		// Wrong Product Send Label
		// Send product to wrong product container
		lb_wrong_product_button_send_to_service.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				pa = PackageController.getPackage(packageID);

				int packageProductAmount = pa.getProductAmount();

				ContainerController.insertProductInWrongProductContainer(pa.getProductID(), packageProductAmount);
				PackageController.deletePackage(packageID);
				packageIDs.remove(packageID);

				lb_product_icon_product.removePaintListener(paint);

				if (!packageIDs.isEmpty()) {
					showPackageBlue(palletID);
				} else {
					showEmpty(palletID);
				}
			}
		});

		
		// Back MenuItem
		// From wrong product to product
		mntm_wrong_product_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showProduct(packageID);
			}
		});

		
		// Exit MenuItem
		// From wrong product to pallet
		mntm_wrong_product_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalletBlue();
			}
		});
		

		// Empty Yes Label
		// Pallet is empty
		lb_empty_button_yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showPalletBlue();
			}
		});

		
		// Empty No Label
		// Pallet is not empty
		lb_empty_button_no.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showInsertPackage();
			}
		});

		
		// Insert Product Label
		// Insert product on palett
		lb_insert_product_button_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String insertPackageID = txt_insert_package_packageID.getText();
				String insertProductIDText = txt_insert_package_productID.getText();
				String insertProductAmountText = txt_insert_package_productAmount.getText();

				if (insertPackageID.isEmpty() || insertProductIDText.isEmpty() || insertProductAmountText.isEmpty()) {
					return;
				} else {
					if (checkID(insertPackageID)) {
						if (checkNumber(String.valueOf(insertProductIDText))
								&& checkNumber(String.valueOf(insertProductAmountText))) {
							int insertProductID = Integer.parseInt(txt_insert_package_productID.getText());
							int insertProductAmount = Integer.parseInt(txt_insert_package_productAmount.getText());

							insertPackage(insertPackageID, palletID, insertProductID, insertProductAmount);
						} else {
							return;
						}
					} else {
						return;
					}
				}
			}
		});

		
		// Menu Icon Label
		// Menu Icon visible
		lb_package_icon_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				menu_package.setVisible(true);
			}
		});

		
		// Menu Icon Label
		// Menu Icon visible
		lb_product_icon_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				menu_product.setVisible(true);
			}
		});

		
		// Menu Icon Label
		// Menu Icon visible
		lb_bad_quality_icon_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				menu_bad_quality.setVisible(true);
			}
		});

		
		// Menu Icon Label
		// Menu Icon visible
		lb_wrong_product_icon_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				menu_wrong_product.setVisible(true);
			}
		});

		
		// Menu Icon Label
		// Menu Icon visible
		lb_insert_package_icon_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				menu_insert_package.setVisible(true);
			}
		});
	}

	
	// Check if only digits
	private boolean checkNumber(String input) {
		if (input.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	
	// Check if valid id format
	private boolean checkID(String input) {
		if (Pattern.matches("^\\d{2}\\.\\d{3}\\.\\d{3}", input)) {
			return true;
		} else {
			return false;
		}
	}

	
	// Insert package on pallet
	private void insertPackage(String packageID, String palletID, int productID, int productAmount) {
		PackageController.insertPackage(packageID, palletID, productID, productAmount);
		this.packageID = packageID;
		packageIDs.add(packageID);
		showProduct(packageID);
	}

	
	// Change product amount
	private void changeProductAmount(int productAmount, int currentProductAmount) {
		if (increaseAmount || productAmount >= currentProductAmount) {
			
		} else {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			messageBox.setMessage("The maximum number of products is exceeded. Continue?");
			messageBox.setText("Confirm Continue");
			int response = messageBox.open();

			if (response == SWT.YES) {
				increaseAmount = true;
				ProductController.changeProductAmount(packageID, currentProductAmount);
				txt_product_amount.setText(String.valueOf(currentProductAmount));
			} else {
				return;
			}
		}
	}

	
	// Increase product amount
	private void increaseProductAmount(int productAmount, int currentProductAmount) {
		if (increaseAmount || productAmount > currentProductAmount) {
			currentProductAmount++;

			txt_product_amount.setText(String.valueOf(currentProductAmount));
			txt_product_amount.getParent().layout();
		} else {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			messageBox.setMessage("The maximum number of products is exceeded. Continue?");
			messageBox.setText("Confirm Continue");
			int response = messageBox.open();

			if (response == SWT.YES) {
				increaseAmount = true;
				
				currentProductAmount++;

				txt_product_amount.setText(String.valueOf(currentProductAmount));
				txt_product_amount.getParent().layout();
			} else {
				return;
			}
		}
	}

	
	//Decrease product amount
	private void decreaseProductAmount(int productAmount) {
		if (productAmount == 1) {
			return;
		} else {
			productAmount--;

			txt_product_amount.setText(String.valueOf(productAmount));
			txt_product_amount.getParent().layout();
		}
	}

	
	// Show pallet (blue:standard)
	private void showPalletBlue() {
		packageIDs = new ArrayList<String>();

		lb_pallet_icon.setImage(pallet_blue);
		txt_pallet_id.setText("");

		stackLayout.topControl = composite_pallet;
		composite_stack.layout();
	}

	
	// Show pallet (green:success)
	private void showPalletGreen(final String palletID) {
		changeColor = true;

		lb_pallet_icon.setImage(pallet_green);

		new Thread(new Runnable() {
			public void run() {
				while (changeColor) {
					try {
						Thread.sleep(1500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							showPackageBlue(palletID);
						}
					});
					changeColor = false;
				}
			}
		}).start();
	}

	
	// Show pallet (red:failed)
	private void showPalletRed() {
		changeColor = true;

		lb_pallet_icon.setImage(pallet_red);

		new Thread(new Runnable() {
			public void run() {
				while (changeColor) {
					try {
						Thread.sleep(1500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							showPalletBlue();
						}
					});
					changeColor = false;
				}
			}

		}).start();
	}

	
	// Show package (blue:standard)
	private void showPackageBlue(String palletID) {
		lb_package_text_pallet.setText("Pallet: " + palletID);
		lb_package_icon_menu.setMenu(menu_package);
		lb_package_icon.setImage(package_blue);
		txt_package_id.setText("");

		stackLayout.topControl = composite_package;
		lb_package_text_pallet.getParent().layout();
		composite_stack.layout();
	}

	
	// Show package (green:success)
	private void showPackageGreen(final String packageID) {
		changeColor = true;

		lb_package_icon_menu.setImage(null);
		lb_package_icon.setImage(package_green);

		new Thread(new Runnable() {
			public void run() {
				while (changeColor) {
					try {
						Thread.sleep(1500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							showProduct(packageID);
						}
					});
					changeColor = false;
				}
			}
		}).start();
	}

	
	// Show package (red:failed)
	private void showPackageRed(final String palletID) {
		changeColor = true;

		lb_package_icon_menu.setImage(null);
		lb_package_icon.setImage(package_red);

		new Thread(new Runnable() {
			public void run() {
				while (changeColor) {
					try {
						Thread.sleep(1500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							showPackageBlue(palletID);
						}
					});
					changeColor = false;
				}
			}

		}).start();
	}

	
	// Show product
	private void showProduct(String packageID) {
		pa = PackageController.getPackage(packageID);
		pr = ProductController.getProduct(PackageController.getProductID(packageID));

		increaseAmount = false;

		lb_product_text_pallet.setText("Pallet: " + palletID);
		lb_product_text_name.setText(pr.getProductName());
		lb_product_text_pieces.setText(pa.getProductAmount() + " pieces");

		try {
			URL url = new URL(pr.getProductImage());
			InputStream is = url.openStream();
			final Image image = new Image(Display.getCurrent(), is);

			paint = new PaintListener() {
				public void paintControl(PaintEvent e) {
					e.gc.drawImage(image, 0, 0);
				}
			};

			lb_product_icon_product.addPaintListener(paint);
		} catch (Exception e) {
			e.printStackTrace();
		}

		txt_product_amount.setText(String.valueOf(pa.getProductAmount()));
		lb_product_text_container.setText(pa.getContainerID());

		stackLayout.topControl = composite_product;
		lb_product_text_pallet.getParent().layout();
		composite_stack.layout();
	}

	
	// Show bad quality
	private void showBadQuality(String packageID) {
		pa = PackageController.getPackage(packageID);
		pr = ProductController.getProduct(PackageController.getProductID(packageID));

		lb_bad_quality_text_pallet.setText("Pallet: " + palletID);
		lb_bad_quality_text_name.setText(pr.getProductName());
		lb_bad_quality_text_pieces.setText(pa.getProductAmount() + " pieces");

		lb_bad_quality_text_product.setText(pr.getProductName());
		lb_bad_quality_text_container
				.setText(String.valueOf(ContainerController.getBadQualityContainerID(pr.getProductID())));

		stackLayout.topControl = composite_bad_quality;
		lb_bad_quality_text_pallet.getParent().layout();
		composite_stack.layout();
	}

	
	// Show wrong product
	private void showWrongProduct(String packageID) {
		pa = PackageController.getPackage(packageID);
		pr = ProductController.getProduct(PackageController.getProductID(packageID));

		lb_wrong_product_text_pallet.setText("Pallet: " + palletID);
		lb_wrong_product_text_name.setText(pr.getProductName());
		lb_wrong_product_text_pieces.setText(pa.getProductAmount() + " pieces");

		lb_wrong_product_text_product.setText(pr.getProductName());
		lb_wrong_product_text_container
				.setText(String.valueOf(ContainerController.getWrongProductContainerID(pr.getProductID())));

		stackLayout.topControl = composite_wrong_product;
		lb_wrong_product_text_pallet.getParent().layout();
		composite_stack.layout();
	}

	
	// Show empty
	private void showEmpty(final String palletID) {
		lb_empty_text_pallet.setText("Pallet: " + palletID);

		stackLayout.topControl = composite_empty;
		lb_empty_text_pallet.getParent().layout();
		composite_stack.layout();
	}

	
	// Show insert package
	private void showInsertPackage() {
		lb_insert_package_text_pallet.setText("Pallet: " + palletID);

		txt_insert_package_packageID.setText("");
		txt_insert_package_productID.setText("");
		txt_insert_package_productAmount.setText("");

		stackLayout.topControl = composite_insert_package;
		lb_insert_package_text_pallet.getParent().layout();
		composite_stack.layout();
	}
    // ----------------------------------------------------------------------------------------------------
	
	
	// GUI Structure
    // ----------------------------------------------------------------------------------------------------
	/**
	 * @wbp.parser.entryPoint
	 */
	private void createContents() {
		shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setMinimumSize(new Point(1280, 720));
		shell.setLayout(new BorderLayout(0, 0));
		shell.setText("Happy Flow");
		shell.setSize(1280, 720);
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		composite_stack = new Composite(shell, SWT.NONE);
		composite_stack.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_stack.setLayoutData(BorderLayout.CENTER);
		stackLayout = new StackLayout();
		composite_stack.setLayout(stackLayout);

		composite_pallet = new Composite(composite_stack, SWT.NONE);
		composite_pallet.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_pallet.setLayout(new BorderLayout(0, 0));

		composite_pallet_north = new Composite(composite_pallet, SWT.NONE);
		composite_pallet_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_pallet_north.setLayoutData(BorderLayout.NORTH);
		composite_pallet_north.setLayout(new GridLayout(1, false));

		lb_pallet_text_decanting = new Label(composite_pallet_north, SWT.NONE);
		lb_pallet_text_decanting.setText("Decanting");
		lb_pallet_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_pallet_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		lb_pallet_text_pallet = new Label(composite_pallet_north, SWT.NONE);
		lb_pallet_text_pallet.setText("No pallet selected");
		lb_pallet_text_pallet.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_pallet_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_pallet_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		composite_pallet_center = new Composite(composite_pallet, SWT.NONE);
		composite_pallet_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_pallet_center.setLayoutData(BorderLayout.CENTER);
		composite_pallet_center.setLayout(new GridLayout(1, false));
		new Label(composite_pallet_center, SWT.NONE);

		lb_pallet_text_scan = new Label(composite_pallet_center, SWT.CENTER);
		lb_pallet_text_scan.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_pallet_text_scan.setText("Scan a pallet");
		lb_pallet_text_scan.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_pallet_text_scan.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_pallet_text_scan.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_pallet_text_scan.setAlignment(SWT.CENTER);

		lb_pallet_icon = new Label(composite_pallet_center, SWT.NONE);
		lb_pallet_icon.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lb_pallet_icon.setImage(pallet_blue);
		lb_pallet_icon.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_pallet_icon.setAlignment(SWT.CENTER);
		composite_stack.layout();

		composite_pallet_south = new Composite(composite_pallet, SWT.NONE);
		composite_pallet_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_pallet_south.setLayoutData(BorderLayout.SOUTH);
		composite_pallet_south.setLayout(new GridLayout(1, false));

		txt_pallet_id = new Text(composite_pallet_south, SWT.BORDER | SWT.CENTER);
		GridData gd_txt_pallet_id = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_txt_pallet_id.widthHint = 203;
		txt_pallet_id.setLayoutData(gd_txt_pallet_id);
		txt_pallet_id.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txt_pallet_id.setFont(SWTResourceManager.getFont("Courier New", 15, SWT.NORMAL));
		new Label(composite_pallet_south, SWT.NONE);

		lb_pallet_button_enter = new Label(composite_pallet_south, SWT.CENTER);
		lb_pallet_button_enter.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lb_pallet_button_enter.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lb_pallet_button_enter.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_pallet_button_enter.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/enter_button.png"));
		lb_pallet_button_enter.setAlignment(SWT.CENTER);

		composite_package = new Composite(composite_stack, SWT.NONE);
		composite_package.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_package.setLayout(new BorderLayout(0, 0));

		composite_package_north = new Composite(composite_package, SWT.NONE);
		composite_package_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_package_north.setLayoutData(BorderLayout.NORTH);
		composite_package_north.setLayout(new GridLayout(5, false));

		lb_package_text_decanting = new Label(composite_package_north, SWT.NONE);
		lb_package_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_package_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_text_decanting.setText("Decanting");
		new Label(composite_package_north, SWT.NONE);
		new Label(composite_package_north, SWT.NONE);
		new Label(composite_package_north, SWT.NONE);

		lb_package_icon_menu = new Label(composite_package_north, SWT.NONE);
		lb_package_icon_menu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_icon_menu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 2));
		lb_package_icon_menu.setAlignment(SWT.CENTER);
		lb_package_icon_menu.setImage(menu);

		menu_package = new Menu(lb_package_icon_menu);
		lb_package_icon_menu.setMenu(menu_package);

		mntm_package_back = new MenuItem(menu_package, SWT.NONE);
		mntm_package_back.setText("Back");

		mntm_package_exit = new MenuItem(menu_package, SWT.NONE);
		mntm_package_exit.setText("Exit");

		lb_package_text_pallet = new Label(composite_package_north, SWT.NONE);
		lb_package_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_text_pallet.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_package_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_package_text_pallet.setText("Pallet:");
		new Label(composite_package_north, SWT.NONE);
		new Label(composite_package_north, SWT.NONE);
		new Label(composite_package_north, SWT.NONE);

		composite_package_center = new Composite(composite_package, SWT.NONE);
		composite_package_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_package_center.setLayoutData(BorderLayout.CENTER);
		composite_package_center.setLayout(new GridLayout(1, false));
		new Label(composite_package_center, SWT.NONE);

		lb_package_text_scan = new Label(composite_package_center, SWT.CENTER);
		lb_package_text_scan.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_package_text_scan.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_package_text_scan.setText("Scan a package");
		lb_package_text_scan.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_package_text_scan.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_text_scan.setAlignment(SWT.CENTER);

		lb_package_icon = new Label(composite_package_center, SWT.NONE);
		lb_package_icon.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lb_package_icon.setImage(package_blue);
		lb_package_icon.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_icon.setAlignment(SWT.CENTER);

		composite_package_south = new Composite(composite_package, SWT.NONE);
		composite_package_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_package_south.setLayoutData(BorderLayout.SOUTH);
		composite_package_south.setLayout(new GridLayout(1, false));

		txt_package_id = new Text(composite_package_south, SWT.BORDER | SWT.CENTER);
		txt_package_id.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txt_package_id.setFont(SWTResourceManager.getFont("Courier New", 15, SWT.NORMAL));
		GridData gd_txt_package_id = new GridData(SWT.CENTER, SWT.FILL, true, true, 1, 1);
		gd_txt_package_id.widthHint = 221;
		txt_package_id.setLayoutData(gd_txt_package_id);
		new Label(composite_package_south, SWT.NONE);

		lb_package_button_enter = new Label(composite_package_south, SWT.CENTER);
		lb_package_button_enter.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_package_button_enter.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lb_package_button_enter.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/enter_button.png"));
		lb_package_button_enter.setAlignment(SWT.CENTER);

		composite_product = new Composite(composite_stack, SWT.NONE);
		composite_product.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_product.setLayout(new BorderLayout(0, 0));

		composite_product_north = new Composite(composite_product, SWT.NONE);
		composite_product_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_product_north.setLayoutData(BorderLayout.NORTH);
		composite_product_north.setLayout(new GridLayout(9, false));

		lb_product_text_decanting = new Label(composite_product_north, SWT.NONE);
		lb_product_text_decanting.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_product_text_decanting.setText("Decanting");
		lb_product_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_product_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_product_north, SWT.NONE);

		lb_product_icon_arrow = new Label(composite_product_north, SWT.NONE);
		lb_product_icon_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_arrow.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/arrow.png"));
		lb_product_icon_arrow.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_product_icon_arrow.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 2));
		new Label(composite_product_north, SWT.NONE);

		lb_product_text_name = new Label(composite_product_north, SWT.NONE);
		lb_product_text_name.setText("Product name");
		lb_product_text_name.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lb_product_text_name.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_product_text_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_product_north, SWT.NONE);
		new Label(composite_product_north, SWT.NONE);
		new Label(composite_product_north, SWT.NONE);

		lb_product_icon_menu = new Label(composite_product_north, SWT.NONE);
		lb_product_icon_menu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_menu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 2));
		lb_product_icon_menu.setAlignment(SWT.CENTER);
		lb_product_icon_menu.setImage(menu);

		menu_product = new Menu(lb_product_icon_menu);
		lb_product_icon_menu.setMenu(menu_product);

		mntmBadQuality = new MenuItem(menu_product, SWT.NONE);
		mntmBadQuality.setText("Bad quality");

		mntmWrongProduct = new MenuItem(menu_product, SWT.NONE);
		mntmWrongProduct.setText("Wrong product");

		mntmChangeAmount = new MenuItem(menu_product, SWT.NONE);
		mntmChangeAmount.setText("Change amount");

		mntm_product_back = new MenuItem(menu_product, SWT.NONE);
		mntm_product_back.setText("Back");

		mntm_product_exit = new MenuItem(menu_product, SWT.NONE);
		mntm_product_exit.setText("Exit");

		lb_product_text_pallet = new Label(composite_product_north, SWT.NONE);
		lb_product_text_pallet.setText("Pallet:");
		lb_product_text_pallet.setForeground(SWTResourceManager.getColor(192, 192, 192));
		lb_product_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_product_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_product_north, SWT.NONE);
		new Label(composite_product_north, SWT.NONE);

		lb_product_text_pieces = new Label(composite_product_north, SWT.NONE);
		lb_product_text_pieces.setText("pieces");
		lb_product_text_pieces.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_product_text_pieces.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_product_text_pieces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_product_north, SWT.NONE);
		new Label(composite_product_north, SWT.NONE);
		new Label(composite_product_north, SWT.NONE);

		composite_product_center = new Composite(composite_product, SWT.NONE);
		composite_product_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_product_center.setLayoutData(BorderLayout.CENTER);
		composite_product_center.setLayout(new BorderLayout(0, 0));

		composite_product_center_center = new Composite(composite_product_center, SWT.NONE);
		composite_product_center_center.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_product_center_center.setLayoutData(BorderLayout.CENTER);
		composite_product_center_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_product_center_center.setLayout(null);

		txt_product_amount = new Text(composite_product_center_center, SWT.CENTER);
		txt_product_amount.setText("1");
		txt_product_amount.setForeground(SWTResourceManager.getColor(255, 255, 255));
		txt_product_amount.setBackground(SWTResourceManager.getColor(0, 136, 242));
		txt_product_amount.setFont(SWTResourceManager.getFont("Arial", 30, SWT.NORMAL));
		txt_product_amount.setBounds(597, 290, 195, 45);

		lb_product_icon_product = new Label(composite_product_center_center, SWT.CENTER);
		lb_product_icon_product.setBackground(SWTResourceManager.getColor(242, 242, 242));
		lb_product_icon_product.setAlignment(SWT.CENTER);
		lb_product_icon_product.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_product.setBounds(235, 183, 256, 256);
		lb_product_icon_product.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/rectangle.png"));

		lb_product_icon_product_arrow = new Label(composite_product_center_center, SWT.NONE);
		lb_product_icon_product_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_product_arrow.setBounds(50, 227, 810, 167);
		lb_product_icon_product_arrow.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/product_arrow.png"));
		lb_product_icon_product_arrow.setAlignment(SWT.CENTER);

		lb_product_text_container = new Label(composite_product_center_center, SWT.CENTER);
		lb_product_text_container.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_product_text_container.setAlignment(SWT.CENTER);
		lb_product_text_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_text_container.setFont(SWTResourceManager.getFont("Arial", 15, SWT.NORMAL));
		lb_product_text_container.setBounds(1025, 307, 105, 20);
		lb_product_text_container.setText("15.092.000");

		lb_product_icon_container = new Label(composite_product_center_center, SWT.NONE);
		lb_product_icon_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_container.setBounds(950, 185, 256, 256);
		lb_product_icon_container.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/container.png"));

		lb_product_icon_plus = new Label(composite_product_center_center, SWT.NONE);
		lb_product_icon_plus.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_plus.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/plus.png"));
		lb_product_icon_plus.setAlignment(SWT.CENTER);
		lb_product_icon_plus.setBounds(595, 135, 195, 64);

		lb_product_icon_minus = new Label(composite_product_center_center, SWT.NONE);
		lb_product_icon_minus.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_icon_minus.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/minus.png"));
		lb_product_icon_minus.setAlignment(SWT.CENTER);
		lb_product_icon_minus.setBounds(595, 422, 195, 64);

		composite_product_south = new Composite(composite_product, SWT.NONE);
		composite_product_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_product_south.setLayoutData(BorderLayout.SOUTH);
		composite_product_south.setLayout(new GridLayout(1, false));

		lb_product_button_confirm_amount = new Label(composite_product_south, SWT.CENTER);
		lb_product_button_confirm_amount.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_product_button_confirm_amount.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/confirm_amount_button.png"));
		lb_product_button_confirm_amount.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_product_button_confirm_amount.setAlignment(SWT.CENTER);
		FormData fd_lb_product_amount = new FormData();
		fd_lb_product_amount.bottom = new FormAttachment(0, 335);
		fd_lb_product_amount.right = new FormAttachment(0, 815);
		fd_lb_product_amount.top = new FormAttachment(0, 295);
		fd_lb_product_amount.left = new FormAttachment(0, 620);
		FormData fd_lb_product_text_product_box = new FormData();
		fd_lb_product_text_product_box.right = new FormAttachment(0, 1162);
		fd_lb_product_text_product_box.top = new FormAttachment(0, 312);
		fd_lb_product_text_product_box.left = new FormAttachment(0, 1045);
		FormData fd_lb_product_icon_product_arrow = new FormData();
		fd_lb_product_icon_product_arrow.bottom = new FormAttachment(0, 599);
		fd_lb_product_icon_product_arrow.right = new FormAttachment(0, 973);
		fd_lb_product_icon_product_arrow.top = new FormAttachment(0, 32);
		fd_lb_product_icon_product_arrow.left = new FormAttachment(0, -35);
		FormData fd_lb_product_icon_product_box = new FormData();
		fd_lb_product_icon_product_box.bottom = new FormAttachment(0, 631);
		fd_lb_product_icon_product_box.right = new FormAttachment(0, 1231);
		fd_lb_product_icon_product_box.top = new FormAttachment(0);
		fd_lb_product_icon_product_box.left = new FormAttachment(0, 975);

		stackLayout.topControl = composite_pallet;

		composite_bad_quality = new Composite(composite_stack, SWT.NONE);
		composite_bad_quality.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_bad_quality.setLayout(new BorderLayout(0, 0));

		composite_bad_quality_north = new Composite(composite_bad_quality, SWT.NONE);
		composite_bad_quality_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_bad_quality_north.setLayoutData(BorderLayout.NORTH);
		composite_bad_quality_north.setLayout(new GridLayout(9, false));

		lb_bad_quality_text_decanting = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_text_decanting.setText("Decanting");
		lb_bad_quality_text_decanting.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_bad_quality_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_bad_quality_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_bad_quality_north, SWT.NONE);

		lb_bad_quality_icon_arrow = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_icon_arrow.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 2));
		lb_bad_quality_icon_arrow.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/arrow.png"));
		lb_bad_quality_icon_arrow.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_bad_quality_icon_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_bad_quality_north, SWT.NONE);

		lb_bad_quality_text_name = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_text_name.setText("Product name");
		lb_bad_quality_text_name.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lb_bad_quality_text_name.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_bad_quality_text_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_bad_quality_north, SWT.NONE);
		new Label(composite_bad_quality_north, SWT.NONE);
		new Label(composite_bad_quality_north, SWT.NONE);

		lb_bad_quality_icon_menu = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_icon_menu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_icon_menu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 2));
		lb_bad_quality_icon_menu.setAlignment(SWT.CENTER);
		lb_bad_quality_icon_menu.setImage(menu);

		menu_bad_quality = new Menu(lb_bad_quality_icon_menu);
		lb_bad_quality_icon_menu.setMenu(menu_bad_quality);

		mntm_bad_quality_back = new MenuItem(menu_bad_quality, SWT.NONE);
		mntm_bad_quality_back.setText("Back");

		mntm_bad_quality_exit = new MenuItem(menu_bad_quality, SWT.NONE);
		mntm_bad_quality_exit.setText("Exit");

		lb_bad_quality_text_pallet = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_text_pallet.setText("Pallet:");
		lb_bad_quality_text_pallet.setForeground(SWTResourceManager.getColor(192, 192, 192));
		lb_bad_quality_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_bad_quality_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_bad_quality_north, SWT.NONE);
		new Label(composite_bad_quality_north, SWT.NONE);

		lb_bad_quality_text_pieces = new Label(composite_bad_quality_north, SWT.NONE);
		lb_bad_quality_text_pieces.setText("pieces");
		lb_bad_quality_text_pieces.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_bad_quality_text_pieces.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_bad_quality_text_pieces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_bad_quality_north, SWT.NONE);
		new Label(composite_bad_quality_north, SWT.NONE);
		new Label(composite_bad_quality_north, SWT.NONE);

		composite_bad_quality_center = new Composite(composite_bad_quality, SWT.NONE);
		composite_bad_quality_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_bad_quality_center.setLayoutData(BorderLayout.CENTER);
		composite_bad_quality_center.setLayout(new BorderLayout(0, 0));

		composite_bad_quality_center_center = new Composite(composite_bad_quality_center, SWT.NONE);
		composite_bad_quality_center_center.setLayout(null);
		composite_bad_quality_center_center.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_bad_quality_center_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_bad_quality_center_center.setLayoutData(BorderLayout.CENTER);

		lb_bad_quality_text_place_package_in_container = new Label(composite_bad_quality_center_center, SWT.CENTER);
		lb_bad_quality_text_place_package_in_container
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_text_place_package_in_container.setAlignment(SWT.LEFT);
		lb_bad_quality_text_place_package_in_container.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_bad_quality_text_place_package_in_container.setBounds(295, 270, 350, 35);
		lb_bad_quality_text_place_package_in_container.setText("Place package in container");

		lb_bad_quality_text_product = new Label(composite_bad_quality_center_center, SWT.CENTER);
		lb_bad_quality_text_product.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_text_product.setFont(SWTResourceManager.getFont("Arial", 20, SWT.BOLD));
		lb_bad_quality_text_product.setAlignment(SWT.LEFT);
		lb_bad_quality_text_product.setBounds(295, 315, 397, 35);

		lb_bad_quality_icon_bad_quality_arrow = new Label(composite_bad_quality_center_center, SWT.NONE);
		lb_bad_quality_icon_bad_quality_arrow.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/bad_quality_arrow.png"));
		lb_bad_quality_icon_bad_quality_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_icon_bad_quality_arrow.setAlignment(SWT.CENTER);
		lb_bad_quality_icon_bad_quality_arrow.setBounds(50, 227, 810, 167);

		lb_bad_quality_text_container = new Label(composite_bad_quality_center_center, SWT.CENTER);
		lb_bad_quality_text_container.setText("15.092.000");
		lb_bad_quality_text_container.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_bad_quality_text_container.setFont(SWTResourceManager.getFont("Arial", 15, SWT.NORMAL));
		lb_bad_quality_text_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_text_container.setAlignment(SWT.CENTER);
		lb_bad_quality_text_container.setBounds(1025, 307, 105, 20);

		lb_bad_quality_icon_container = new Label(composite_bad_quality_center_center, SWT.NONE);
		lb_bad_quality_icon_container.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/container.png"));
		lb_bad_quality_icon_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_icon_container.setBounds(950, 185, 256, 256);

		composite_bad_quality_south = new Composite(composite_bad_quality, SWT.NONE);
		composite_bad_quality_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_bad_quality_south.setLayoutData(BorderLayout.SOUTH);
		composite_bad_quality_south.setLayout(new GridLayout(1, false));

		lb_bad_quality_button_send_to_quarantine = new Label(composite_bad_quality_south, SWT.CENTER);
		lb_bad_quality_button_send_to_quarantine.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_bad_quality_button_send_to_quarantine.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/send_to_quarantine_button.png"));
		lb_bad_quality_button_send_to_quarantine.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_bad_quality_button_send_to_quarantine.setAlignment(SWT.CENTER);

		composite_empty = new Composite(composite_stack, SWT.NONE);
		composite_empty.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_empty.setLayout(new BorderLayout(0, 0));

		composite_empty_north = new Composite(composite_empty, SWT.NONE);
		composite_empty_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_empty_north.setLayoutData(BorderLayout.NORTH);
		composite_empty_north.setLayout(new GridLayout(1, false));

		lb_empty_text_decanting = new Label(composite_empty_north, SWT.NONE);
		lb_empty_text_decanting.setText("Decanting");
		lb_empty_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_empty_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		lb_empty_text_pallet = new Label(composite_empty_north, SWT.NONE);
		lb_empty_text_pallet.setText("Pallet:");
		lb_empty_text_pallet.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_empty_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_empty_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		composite_empty_center = new Composite(composite_empty, SWT.NONE);
		composite_empty_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_empty_center.setLayoutData(BorderLayout.CENTER);
		composite_empty_center.setLayout(new GridLayout(1, false));
		new Label(composite_empty_center, SWT.NONE);

		lb_empty_text_empty = new Label(composite_empty_center, SWT.CENTER);
		lb_empty_text_empty.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_empty_text_empty.setText("Confirm load carrier is empty");
		lb_empty_text_empty.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_empty_text_empty.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_empty_text_empty.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_empty_text_empty.setAlignment(SWT.CENTER);

		lb_empty_icon = new Label(composite_empty_center, SWT.NONE);
		lb_empty_icon.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lb_empty_icon.setImage(pallet_grey);
		lb_empty_icon.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_empty_icon.setAlignment(SWT.CENTER);

		composite_empty_south = new Composite(composite_empty, SWT.NONE);
		composite_empty_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_empty_south.setLayoutData(BorderLayout.SOUTH);
		composite_empty_south.setLayout(new GridLayout(1, false));
		new Label(composite_empty_south, SWT.NONE);

		lb_empty_button_yes = new Label(composite_empty_south, SWT.CENTER);
		lb_empty_button_yes.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_empty_button_yes.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/yes_button.png"));
		lb_empty_button_yes.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_empty_button_yes.setAlignment(SWT.CENTER);
		new Label(composite_empty_south, SWT.NONE);

		lb_empty_button_no = new Label(composite_empty_south, SWT.CENTER);
		lb_empty_button_no.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_empty_button_no.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/no_button.png"));
		lb_empty_button_no.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_empty_button_no.setAlignment(SWT.CENTER);

		composite_wrong_product = new Composite(composite_stack, SWT.NONE);
		composite_wrong_product.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_wrong_product.setLayout(new BorderLayout(0, 0));

		composite_wrong_product_north = new Composite(composite_wrong_product, SWT.NONE);
		composite_wrong_product_north.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_wrong_product_north.setLayoutData(BorderLayout.NORTH);
		composite_wrong_product_north.setLayout(new GridLayout(9, false));

		lb_wrong_product_text_decanting = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_text_decanting.setText("Decanting");
		lb_wrong_product_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_wrong_product_text_decanting.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_wrong_product_north, SWT.NONE);

		lb_wrong_product_icon_arrow = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_icon_arrow.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		lb_wrong_product_icon_arrow.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/arrow.png"));
		lb_wrong_product_icon_arrow.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_wrong_product_icon_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_wrong_product_north, SWT.NONE);

		lb_wrong_product_text_name = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_text_name.setText("Product name");
		lb_wrong_product_text_name.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lb_wrong_product_text_name.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_wrong_product_text_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_wrong_product_north, SWT.NONE);
		new Label(composite_wrong_product_north, SWT.NONE);
		new Label(composite_wrong_product_north, SWT.NONE);

		lb_wrong_product_icon_menu = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_icon_menu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_icon_menu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 2));
		lb_wrong_product_icon_menu.setImage(menu);
		lb_wrong_product_icon_menu.setAlignment(SWT.CENTER);

		menu_wrong_product = new Menu(lb_wrong_product_icon_menu);
		lb_wrong_product_icon_menu.setMenu(menu_wrong_product);

		mntm_wrong_product_back = new MenuItem(menu_wrong_product, SWT.NONE);
		mntm_wrong_product_back.setText("Back");

		mntm_wrong_product_exit = new MenuItem(menu_wrong_product, SWT.NONE);
		mntm_wrong_product_exit.setText("Exit");

		lb_wrong_product_text_pallet = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_text_pallet.setText("Pallet:");
		lb_wrong_product_text_pallet.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_wrong_product_text_pallet.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_wrong_product_text_pallet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_wrong_product_north, SWT.NONE);
		new Label(composite_wrong_product_north, SWT.NONE);

		lb_wrong_product_text_pieces = new Label(composite_wrong_product_north, SWT.NONE);
		lb_wrong_product_text_pieces.setText("pieces");
		lb_wrong_product_text_pieces.setForeground(SWTResourceManager.getColor(192, 192, 192));
		lb_wrong_product_text_pieces.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lb_wrong_product_text_pieces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		new Label(composite_wrong_product_north, SWT.NONE);
		new Label(composite_wrong_product_north, SWT.NONE);
		new Label(composite_wrong_product_north, SWT.NONE);

		composite_wrong_product_center = new Composite(composite_wrong_product, SWT.NONE);
		composite_wrong_product_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_wrong_product_center.setLayoutData(BorderLayout.CENTER);
		composite_wrong_product_center.setLayout(new BorderLayout(0, 0));

		composite_wrong_product_center_center = new Composite(composite_wrong_product_center, SWT.NONE);
		composite_wrong_product_center_center.setLayout(null);
		composite_wrong_product_center_center.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_wrong_product_center_center.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_wrong_product_center_center.setLayoutData(BorderLayout.CENTER);

		lb_wrong_product_text_place_package_in_container = new Label(composite_wrong_product_center_center, SWT.CENTER);
		lb_wrong_product_text_place_package_in_container.setText("Place package in container");
		lb_wrong_product_text_place_package_in_container.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_wrong_product_text_place_package_in_container
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_text_place_package_in_container.setAlignment(SWT.LEFT);
		lb_wrong_product_text_place_package_in_container.setBounds(295, 270, 350, 35);

		lb_wrong_product_text_product = new Label(composite_wrong_product_center_center, SWT.CENTER);
		lb_wrong_product_text_product.setFont(SWTResourceManager.getFont("Arial", 20, SWT.BOLD));
		lb_wrong_product_text_product.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_text_product.setAlignment(SWT.LEFT);
		lb_wrong_product_text_product.setBounds(295, 315, 397, 35);

		lb_wrong_product_icon_wrong_item_arrow = new Label(composite_wrong_product_center_center, SWT.NONE);
		lb_wrong_product_icon_wrong_item_arrow.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/wrong_item_arrow.png"));
		lb_wrong_product_icon_wrong_item_arrow.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_icon_wrong_item_arrow.setAlignment(SWT.CENTER);
		lb_wrong_product_icon_wrong_item_arrow.setBounds(50, 227, 810, 167);

		lb_wrong_product_text_container = new Label(composite_wrong_product_center_center, SWT.CENTER);
		lb_wrong_product_text_container.setText("15.092.000");
		lb_wrong_product_text_container.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_wrong_product_text_container.setFont(SWTResourceManager.getFont("Arial", 15, SWT.NORMAL));
		lb_wrong_product_text_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_text_container.setAlignment(SWT.CENTER);
		lb_wrong_product_text_container.setBounds(1025, 307, 105, 20);

		lb_wrong_product_icon_container = new Label(composite_wrong_product_center_center, SWT.NONE);
		lb_wrong_product_icon_container.setImage(
				SWTResourceManager.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/container.png"));
		lb_wrong_product_icon_container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_icon_container.setBounds(950, 185, 256, 256);

		composite_wrong_product_south = new Composite(composite_wrong_product, SWT.NONE);
		composite_wrong_product_south.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_wrong_product_south.setLayoutData(BorderLayout.SOUTH);
		composite_wrong_product_south.setLayout(new GridLayout(1, false));

		lb_wrong_product_button_send_to_service = new Label(composite_wrong_product_south, SWT.CENTER);
		lb_wrong_product_button_send_to_service.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lb_wrong_product_button_send_to_service.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/send_to_service_button.png"));
		lb_wrong_product_button_send_to_service.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_wrong_product_button_send_to_service.setAlignment(SWT.CENTER);

		composite_insert_package = new Composite(composite_stack, SWT.NONE);
		composite_insert_package.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_insert_package.setLayout(new BorderLayout(0, 0));

		composite_insert_package_north = new Composite(composite_insert_package, SWT.NONE);
		composite_insert_package_north.setLayoutData(BorderLayout.NORTH);
		composite_insert_package_north.setLayout(new GridLayout(5, false));

		lb_insert_package_text_decanting = new Label(composite_insert_package_north, SWT.NONE);
		lb_insert_package_text_decanting.setFont(SWTResourceManager.getFont("Arial", 13, SWT.BOLD));
		lb_insert_package_text_decanting.setText("Decanting");
		new Label(composite_insert_package_north, SWT.NONE);
		new Label(composite_insert_package_north, SWT.NONE);
		new Label(composite_insert_package_north, SWT.NONE);

		lb_insert_package_icon_menu = new Label(composite_insert_package_north, SWT.NONE);
		GridData gd_lb_insert_package_icon_menu = new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 2);
		gd_lb_insert_package_icon_menu.widthHint = 1151;
		lb_insert_package_icon_menu.setLayoutData(gd_lb_insert_package_icon_menu);

		menu_insert_package = new Menu(lb_insert_package_icon_menu);
		lb_insert_package_icon_menu.setMenu(menu_insert_package);

		mntm_insert_package_back = new MenuItem(menu_insert_package, SWT.NONE);
		mntm_insert_package_back.setText("Back");

		lb_insert_package_text_pallet = new Label(composite_insert_package_north, SWT.NONE);
		lb_insert_package_text_pallet.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lb_insert_package_text_pallet.setText("No pallet selected");
		new Label(composite_insert_package_north, SWT.NONE);
		new Label(composite_insert_package_north, SWT.NONE);
		new Label(composite_insert_package_north, SWT.NONE);

		composite_insert_package_center = new Composite(composite_insert_package, SWT.NONE);
		composite_insert_package_center.setLayoutData(BorderLayout.CENTER);
		composite_insert_package_center.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_insert_package_center.setLayout(null);

		lb_insert_package_text_insert_a_new_package = new Label(composite_insert_package_center, SWT.CENTER);
		lb_insert_package_text_insert_a_new_package.setBounds(10, 10, 1244, 32);
		lb_insert_package_text_insert_a_new_package.setText("Insert new package");
		lb_insert_package_text_insert_a_new_package.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lb_insert_package_text_insert_a_new_package.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		lb_insert_package_text_insert_a_new_package.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lb_insert_package_text_insert_a_new_package.setAlignment(SWT.CENTER);

		lb_insert_package_text_packageID = new Label(composite_insert_package_center, SWT.NONE);
		lb_insert_package_text_packageID.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lb_insert_package_text_packageID.setBounds(437, 166, 129, 37);
		lb_insert_package_text_packageID.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lb_insert_package_text_packageID.setText("PackageID:");

		txt_insert_package_packageID = new Text(composite_insert_package_center, SWT.BORDER);
		txt_insert_package_packageID.setBounds(632, 163, 243, 43);
		txt_insert_package_packageID.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));

		txt_insert_package_productID = new Text(composite_insert_package_center, SWT.BORDER);
		txt_insert_package_productID.setBounds(632, 224, 243, 43);
		txt_insert_package_productID.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));

		lb_insert_package_text_productID = new Label(composite_insert_package_center, SWT.NONE);
		lb_insert_package_text_productID.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lb_insert_package_text_productID.setBounds(442, 227, 124, 37);
		lb_insert_package_text_productID.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lb_insert_package_text_productID.setText("ProductID:");

		txt_insert_package_productAmount = new Text(composite_insert_package_center, SWT.BORDER);
		txt_insert_package_productAmount.setBounds(632, 283, 243, 43);
		txt_insert_package_productAmount.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));

		lb_insert_product_text_productAmount = new Label(composite_insert_package_center, SWT.NONE);
		lb_insert_product_text_productAmount.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lb_insert_product_text_productAmount.setBounds(366, 286, 200, 37);
		lb_insert_product_text_productAmount.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lb_insert_product_text_productAmount.setText("Product amount:");

		composite_insert_package_south = new Composite(composite_insert_package, SWT.NONE);
		composite_insert_package_south.setLayoutData(BorderLayout.SOUTH);
		composite_insert_package_south.setLayout(new GridLayout(1, false));

		lb_insert_product_button_confirm = new Label(composite_insert_package_south, SWT.NONE);
		lb_insert_product_button_confirm.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true, 1, 1));
		lb_insert_product_button_confirm.setText("Confirm");
		lb_insert_product_button_confirm.setImage(SWTResourceManager
				.getImage("src/main/java/com/ssischaefer/happyflow/resources/icons/enter_button.png"));
		lb_insert_product_button_confirm.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lb_insert_product_button_confirm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lb_insert_product_button_confirm.setAlignment(SWT.CENTER);
		// ----------------------------------------------------------------------------------------------------
	}
}