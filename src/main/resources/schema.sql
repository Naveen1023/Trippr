/*
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "mobile", nullable = false)
  private String mobile;

  // one user can book multiple tickets
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  @Builder.Default
  List<BookingDetailsEntity> bookingDetailsList = new ArrayList<>();
*/